package com.akrasnoyarov.movieandroidacademy.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.akrasnoyarov.movieandroidacademy.R

class MovieListFragment : Fragment() {
    private var callback: Callbacks? = null

    interface Callbacks {
        fun onMovieItemClicked()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        view.findViewById<ConstraintLayout>(R.id.movie_item)?.apply {
            setOnClickListener {
                callback?.onMovieItemClicked()
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Callbacks) {
            callback = context
        }
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}