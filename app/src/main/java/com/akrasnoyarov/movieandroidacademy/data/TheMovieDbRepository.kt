package com.akrasnoyarov.movieandroidacademy.data

import android.content.Context
import com.akrasnoyarov.movieandroidacademy.api.MovieDb
import com.akrasnoyarov.movieandroidacademy.api.ResultsItem
import com.akrasnoyarov.movieandroidacademy.api.RetrofitModule
import com.akrasnoyarov.movieandroidacademy.models.db.MovieRoomDb
import com.akrasnoyarov.movieandroidacademy.models.entities.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TheMovieDbRepository(context: Context) : MovieRepository {
    private var cachedMovies = false
    private val moviesDb: MovieRoomDb by lazy { MovieRoomDb.create(context) }

    override suspend fun loadMovies(): List<ResultsItem?> {
        val movies: List<ResultsItem?>
        return if (!cachedMovies) {
            withContext(Dispatchers.IO) {
                setImageUrl()
                movies = RetrofitModule.moviesApi.getUpcomingMovies().results!!
                addAllMovies(movies)
                cachedMovies = true
                movies
            }
        } else {
            withContext(Dispatchers.IO) {
                movies = getAllMovies()
                movies
            }
        }
    }

    override suspend fun loadMovie(movieId: Int): MovieDb = withContext(Dispatchers.IO) {
        val movie: MovieDb
        if (cachedMovies) {
            movie = getMovieById(movieId)
            movie
        } else {
            RetrofitModule.moviesApi.getMovieById(movieId)
        }
    }

    private suspend fun getMovieById(movieId: Int): MovieDb =
        convertFromDbToMovieDb(moviesDb.getMovieById(movieId.toLong()))

    private suspend fun getAllMovies(): List<ResultsItem> =
        moviesDb.getAllMovies().map { convertFromDbToApi(it) }

    private suspend fun addAllMovies(movies: List<ResultsItem?>) = moviesDb.addAllMovies(
        movies.map { convertFromApiToDb(it!!) }
    )

    private suspend fun setImageUrl() {
        withContext(Dispatchers.IO) {
            val configuration = RetrofitModule.moviesApi.getConfiguration().images
            RetrofitModule.imageUrl = "${configuration?.secureBaseUrl}/original/"
        }
    }

    companion object {
        fun convertFromApiToDb(movieFromApi: ResultsItem): Movie = Movie(
            id = movieFromApi.id,
            title = movieFromApi.title,
            imageUrl = movieFromApi.posterPath,
            rating = null,
            duration = null,
            reviewCount = movieFromApi.voteCount
        )

        private fun convertFromDbToApi(movieFromDb: Movie): ResultsItem = ResultsItem(
            id = movieFromDb.id,
            title = movieFromDb.title,
            posterPath = movieFromDb.imageUrl,
            voteCount = movieFromDb.reviewCount
        )

        private fun convertFromDbToMovieDb(movieFromDb: Movie): MovieDb = MovieDb(
            id = movieFromDb.id,
            title = movieFromDb.title,
            posterPath = movieFromDb.imageUrl,
            voteCount = movieFromDb.reviewCount,
            overview = movieFromDb.overview
        )
    }

}