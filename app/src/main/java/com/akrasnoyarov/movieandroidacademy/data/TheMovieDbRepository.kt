package com.akrasnoyarov.movieandroidacademy.data

import android.util.Log
import com.akrasnoyarov.movieandroidacademy.api.MovieDb
import com.akrasnoyarov.movieandroidacademy.api.ResultsItem
import com.akrasnoyarov.movieandroidacademy.api.RetrofitModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TheMovieDbRepository : MovieRepository {
    private var cachedMovies = mutableListOf<ResultsItem?>()

    override suspend fun loadMovies(): List<ResultsItem?> {
        if (cachedMovies.isNullOrEmpty()) {
            withContext(Dispatchers.IO) {
                setImageUrl()
                RetrofitModule.moviesApi.getUpcomingMovies().results.also { cachedMovies.addAll(it!!) }
            }
        }
        return cachedMovies
    }

    override suspend fun loadMovie(movieId: Int): MovieDb = withContext(Dispatchers.IO) {
        RetrofitModule.moviesApi.getMovieById(movieId)
    }

    private suspend fun setImageUrl() {
        withContext(Dispatchers.IO) {
            val configuration = RetrofitModule.moviesApi.getConfiguration().images
            RetrofitModule.imageUrl = "${configuration?.secureBaseUrl}/original/"
        }
    }
}