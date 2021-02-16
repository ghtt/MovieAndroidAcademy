package com.akrasnoyarov.movieandroidacademy.viewmodels

import com.akrasnoyarov.movieandroidacademy.data.MovieRepository

class ViewModelFactory {
    companion object {
        private lateinit var viewModelInstance: MovieViewModel
        fun getInstance(repository: MovieRepository): MovieViewModel {
            if (!::viewModelInstance.isInitialized) {
                viewModelInstance = MovieViewModel(repository)
            }
            return viewModelInstance
        }
    }
}