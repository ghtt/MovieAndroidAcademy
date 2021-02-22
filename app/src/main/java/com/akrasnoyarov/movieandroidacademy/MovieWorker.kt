package com.akrasnoyarov.movieandroidacademy

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.akrasnoyarov.movieandroidacademy.api.MovieDb
import com.akrasnoyarov.movieandroidacademy.api.RetrofitModule
import com.akrasnoyarov.movieandroidacademy.data.MovieRepository
import com.akrasnoyarov.movieandroidacademy.data.TheMovieDbRepository
import com.akrasnoyarov.movieandroidacademy.models.db.MovieRoomDb


class MovieWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val movies = RetrofitModule.moviesApi.getUpcomingMovies().results!!
        val movieDb = MovieRoomDb.create(applicationContext)

        movieDb.addAllMovies(movies.map { TheMovieDbRepository.convertFromApiToDb(it!!) })

        return Result.success()
    }

}