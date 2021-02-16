package com.akrasnoyarov.movieandroidacademy.data

import android.util.Log
import com.akrasnoyarov.movieandroidacademy.api.RetrofitModule
import com.akrasnoyarov.movieandroidacademy.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TheMovieDbRepository : MovieRepository {
    private val cachedMovies: List<Movie> = listOf()

    override suspend fun loadMovies(): List<Movie> =
        withContext(Dispatchers.IO) {
            val upcomingMovies = RetrofitModule.moviesApi.getUpcomingMovies()
            upcomingMovies.results!!.forEach {
                Log.d("myLogs", it?.title!!)
            }
            cachedMovies;
        }


    override suspend fun loadMovie(movieId: Int): Movie? {
        TODO("Not yet implemented")
    }
}