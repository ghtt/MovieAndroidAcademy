package com.akrasnoyarov.movieandroidacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akrasnoyarov.movieandroidacademy.fragments.MovieDetailsFragment
import com.akrasnoyarov.movieandroidacademy.fragments.MovieListFragment

class MainActivity : SingleFragmentActivity(), MovieListFragment.Callbacks {
    override fun createFragment() = MovieListFragment.newInstance()

    override fun onMovieItemClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieDetailsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}