package com.akrasnoyarov.movieandroidacademy.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akrasnoyarov.movieandroidacademy.R
import com.akrasnoyarov.movieandroidacademy.adapters.ActorViewAdapter
import com.akrasnoyarov.movieandroidacademy.api.MovieDb
import com.akrasnoyarov.movieandroidacademy.api.RetrofitModule
import com.akrasnoyarov.movieandroidacademy.data.TheMovieDbRepository
import com.akrasnoyarov.movieandroidacademy.model.Movie
import com.akrasnoyarov.movieandroidacademy.viewmodels.MovieViewModel
import com.akrasnoyarov.movieandroidacademy.viewmodels.ViewModelFactory
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {
    private var actorsRecyclerView: RecyclerView? = null
    private var repository = TheMovieDbRepository()
    private val viewModel by lazy { ViewModelFactory.getInstance(repository) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val movieId = arguments?.getInt("MOVIE")
        viewModel.loadMovieById(movieId!!)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {

        viewModel.movie.observe(viewLifecycleOwner) {
            view.run {
                Glide.with(activity!!)
                    .load(RetrofitModule.imageUrl.plus(it.posterPath!!))
                    .into(findViewById(R.id.movie_image_view))

                findViewById<TextView>(R.id.rating_text_view).apply {
//                text = movie.pgAge.toString()
                }

                findViewById<TextView>(R.id.movie_title_text_view).apply {
                    text = it.title
                }

                findViewById<TextView>(R.id.movie_genre_text_view).apply {
                    text = it.genres.let {
                        var genreString = ""
                        it?.forEach { item ->
                            genreString = genreString.plus(item?.name).plus(" ")
                        }
                        genreString
                    }
                }

                findViewById<TextView>(R.id.review_count_text_view).apply {
                    text = it.voteCount.toString()
                }

                findViewById<TextView>(R.id.storylineText).apply {
                    text = it.overview
                }
            }
        }
//        actorsRecyclerView = view.findViewById<RecyclerView>(R.id.actors_recycler_view)?.apply {
//            adapter = ActorViewAdapter(movie.)
//            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//        }
    }

    companion object {
        fun newInstance(movieId: Int): MovieDetailsFragment = MovieDetailsFragment().apply {
            arguments = Bundle().apply { putInt("MOVIE", movieId) }
        }
    }
}