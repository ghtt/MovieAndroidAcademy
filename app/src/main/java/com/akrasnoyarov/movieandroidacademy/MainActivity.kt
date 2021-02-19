package com.akrasnoyarov.movieandroidacademy

import com.akrasnoyarov.movieandroidacademy.adapters.MovieViewAdapter
import com.akrasnoyarov.movieandroidacademy.fragments.MovieDetailsFragment
import com.akrasnoyarov.movieandroidacademy.fragments.MovieListFragment

class MainActivity : SingleFragmentActivity(), MovieViewAdapter.OnMovieClickListener {
    override fun createFragment() = MovieListFragment.newInstance()

    override fun onMovieItemClicked(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(movieId))
            .addToBackStack(null)
            .commit()
    }
}