package com.akrasnoyarov.movieandroidacademy.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akrasnoyarov.movieandroidacademy.R
import com.akrasnoyarov.movieandroidacademy.adapters.MovieViewAdapter
import com.akrasnoyarov.movieandroidacademy.data.TheMovieDbRepository
import com.akrasnoyarov.movieandroidacademy.viewmodels.MovieViewModel
import com.akrasnoyarov.movieandroidacademy.viewmodels.ViewModelFactory
import kotlinx.coroutines.*

class MovieListFragment : Fragment() {
    private var moviesRecyclerView: RecyclerView? = null
    private var moviesAdapter: MovieViewAdapter? = null
    private var scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var movieDbRepository: TheMovieDbRepository? = null
    private var listener: MovieViewAdapter.OnMovieClickListener? = null
    private var progressBar: ProgressBar? = null
    private val viewModel by lazy { ViewModelFactory.getInstance(movieDbRepository!!) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieViewAdapter.OnMovieClickListener) {
            listener = context
        }
        movieDbRepository = TheMovieDbRepository()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        initViews(view)
        initAdapter()

        viewModel.movies.observe(viewLifecycleOwner) { moviesAdapter?.setMoviesList(it) }
        viewModel.loading.observe(viewLifecycleOwner) { progressBar?.isVisible = it }

        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        moviesRecyclerView?.adapter = null
        moviesRecyclerView = null
        moviesAdapter = null
    }

    override fun onDetach() {
        super.onDetach()
        scope.cancel()
    }

    private fun initAdapter() {
        moviesAdapter = moviesAdapter ?: MovieViewAdapter(listener)
        moviesRecyclerView?.layoutManager = GridLayoutManager(activity, 2)
        moviesRecyclerView?.adapter = moviesAdapter
    }

    private fun initViews(view: View?) {
        moviesRecyclerView = view?.findViewById(R.id.movies_recycler_view)
        progressBar = view?.findViewById(R.id.progress_bar)
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}