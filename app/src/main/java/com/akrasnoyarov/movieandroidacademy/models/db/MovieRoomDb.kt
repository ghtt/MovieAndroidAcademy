package com.akrasnoyarov.movieandroidacademy.models.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akrasnoyarov.movieandroidacademy.models.dao.MovieDao
import com.akrasnoyarov.movieandroidacademy.models.entities.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieRoomDb : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        private const val DATABASE_NAME = "movies-db.db"
        fun create(applicationContext: Context): MovieRoomDb = Room.databaseBuilder(
            applicationContext,
            MovieRoomDb::class.java,
            DATABASE_NAME
        ).build()
    }

    suspend fun addMovie(movie: Movie) = movieDao.addMovie(movie)

    suspend fun getAllMovies(): List<Movie> = movieDao.getAllMovies()

    suspend fun getMovieById(movieId: Long): Movie = movieDao.getMovieById(movieId)

    suspend fun addAllMovies(movies: List<Movie>) = movieDao.addAllMovies(movies)
}