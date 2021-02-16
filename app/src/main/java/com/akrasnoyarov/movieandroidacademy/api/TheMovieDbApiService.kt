package com.akrasnoyarov.movieandroidacademy.api

import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbApiService {
    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): UpcomingMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id: Int): Movie

    @GET("person/{person_id}")
    suspend fun getPersonById(@Path("person_id") id: Int): Person
}