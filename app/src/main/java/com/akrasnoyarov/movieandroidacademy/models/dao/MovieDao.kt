package com.akrasnoyarov.movieandroidacademy.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akrasnoyarov.movieandroidacademy.models.entities.Movie

@Dao
interface MovieDao {
    @Insert
    suspend fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllMovies(movies: List<Movie>)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovieById(movieId: Long): Movie
}