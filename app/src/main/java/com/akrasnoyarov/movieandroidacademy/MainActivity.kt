package com.akrasnoyarov.movieandroidacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.akrasnoyarov.movieandroidacademy.adapters.MovieViewAdapter
import com.akrasnoyarov.movieandroidacademy.fragments.MovieDetailsFragment
import com.akrasnoyarov.movieandroidacademy.fragments.MovieListFragment
import com.akrasnoyarov.movieandroidacademy.model.Movie

class MainActivity : SingleFragmentActivity(), MovieViewAdapter.OnMovieClickListener {
    override fun createFragment() = MovieListFragment.newInstance()

    override fun onMovieItemClicked(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(movie))
            .addToBackStack(null)
            .commit()
    }
}