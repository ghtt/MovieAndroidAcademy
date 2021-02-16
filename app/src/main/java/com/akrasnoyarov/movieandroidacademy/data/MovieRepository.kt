package com.akrasnoyarov.movieandroidacademy.data

import com.akrasnoyarov.movieandroidacademy.model.Movie

interface MovieRepository {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): Movie?
}