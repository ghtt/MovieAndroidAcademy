package com.akrasnoyarov.movieandroidacademy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akrasnoyarov.movieandroidacademy.api.MovieDb
import com.akrasnoyarov.movieandroidacademy.api.ResultsItem
import com.akrasnoyarov.movieandroidacademy.data.MovieRepository
import kotlinx.coroutines.launch


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private var _movies = MutableLiveData<List<ResultsItem?>>()
    val movies: LiveData<List<ResultsItem?>> get() = _movies

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var _movie = MutableLiveData<MovieDb>()
    val movie: LiveData<MovieDb> get() = _movie

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _loading.postValue(true)
            _movies.value = repository.loadMovies()
            _loading.postValue(false)
        }
    }

    fun loadMovieById(id: Int) {
        viewModelScope.launch {
            _loading.postValue(true)
            _movie.value = repository.loadMovie(id)
            _loading.postValue(false)
        }
    }
}