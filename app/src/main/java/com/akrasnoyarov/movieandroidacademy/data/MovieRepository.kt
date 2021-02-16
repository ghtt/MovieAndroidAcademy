package com.akrasnoyarov.movieandroidacademy.data

import com.akrasnoyarov.movieandroidacademy.api.MovieDb
import com.akrasnoyarov.movieandroidacademy.api.ResultsItem
import com.akrasnoyarov.movieandroidacademy.api.UpcomingMovieResponse

interface MovieRepository{
    suspend fun loadMovies(): List<ResultsItem?>
    suspend fun loadMovie(movieId: Int): MovieDb
}