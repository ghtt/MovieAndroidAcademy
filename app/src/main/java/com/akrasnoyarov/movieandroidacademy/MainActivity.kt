package com.akrasnoyarov.movieandroidacademy

import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.akrasnoyarov.movieandroidacademy.adapters.MovieViewAdapter
import com.akrasnoyarov.movieandroidacademy.fragments.MovieDetailsFragment
import com.akrasnoyarov.movieandroidacademy.fragments.MovieListFragment

class MainActivity : SingleFragmentActivity(), MovieViewAdapter.OnMovieClickListener {
    override fun createFragment() = MovieListFragment.newInstance()
    override fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val movieId = intent.data?.lastPathSegment?.toInt() ?: return
                supportFragmentManager.popBackStack(
                    MOVIE_TAG,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                openMovieDetails(movieId, MOVIE_TAG)
            }
            else -> Log.d("myLogs", "Unknown action!")
        }
    }

    override fun onMovieItemClicked(movieId: Int) {
        openMovieDetails(movieId)
    }

    private fun openMovieDetails(id: Int, tag: String? = null) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(tag)
            .replace(R.id.fragment_container, MovieDetailsFragment.newInstance(id))
            .commit()
    }

    companion object {
        private const val MOVIE_TAG = "movie"
    }

}