package com.akrasnoyarov.movieandroidacademy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akrasnoyarov.movieandroidacademy.data.MovieRepository
import com.akrasnoyarov.movieandroidacademy.model.Movie
import kotlinx.coroutines.launch


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun loadMovies() {
        viewModelScope.launch {
            _movies.value = repository.loadMovies()
        }
    }
}