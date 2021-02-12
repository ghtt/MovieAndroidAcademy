package com.akrasnoyarov.movieandroidacademy.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akrasnoyarov.movieandroidacademy.R
import com.akrasnoyarov.movieandroidacademy.adapters.MovieViewAdapter
import com.akrasnoyarov.movieandroidacademy.data.JsonMovieRepository
import kotlinx.coroutines.*

class MovieListFragment : Fragment() {
    private var moviesRecyclerView: RecyclerView? = null
    private var moviesAdapter: MovieViewAdapter? = null
    private var scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var repository: JsonMovieRepository? = null
    private var listener: MovieViewAdapter.OnMovieClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View?) {
        moviesAdapter = moviesAdapter ?: MovieViewAdapter(listener)
        scope.launch {
            moviesAdapter?.setMoviesList(repository?.loadMovies()!!)
            moviesAdapter?.notifyDataSetChanged()
        }

        moviesRecyclerView = view?.findViewById(R.id.movies_recycler_view)
        moviesRecyclerView?.adapter = moviesAdapter
        moviesRecyclerView?.layoutManager = GridLayoutManager(activity, 2)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieViewAdapter.OnMovieClickListener) {
            listener = context
        }
        repository = JsonMovieRepository(context)
    }

    override fun onDetach() {
        super.onDetach()
        scope.cancel()
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}