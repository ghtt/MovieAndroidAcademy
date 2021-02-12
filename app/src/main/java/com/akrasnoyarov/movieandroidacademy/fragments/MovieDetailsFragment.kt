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
import com.akrasnoyarov.movieandroidacademy.model.Movie
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {
    private var actorsRecyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)

        initUI(view)

        return view
    }

    private fun initUI(view: View) {
        val movie = arguments?.getSerializable("MOVIE") as Movie
        view.run {
            Glide.with(activity!!)
                .load(movie.detailImageUrl)
                .into(findViewById(R.id.movie_image_view))

            findViewById<TextView>(R.id.rating_text_view).apply {
                text = movie.pgAge.toString()
            }

            findViewById<TextView>(R.id.movie_title_text_view).apply {
                text = movie.title
            }

            findViewById<TextView>(R.id.movie_genre_text_view).apply {
                text = movie.genres.let {
                    var genreString = ""
                    it.forEach { item ->
                        genreString = genreString.plus(item.name).plus(" ")
                    }
                    genreString
                }
            }

            findViewById<TextView>(R.id.review_count_text_view).apply {
                text = movie.reviewCount.toString()
            }

            findViewById<TextView>(R.id.storylineText).apply {
                text = movie.storyLine
            }
        }
        actorsRecyclerView = view.findViewById<RecyclerView>(R.id.actors_recycler_view)?.apply {
            adapter = ActorViewAdapter(movie.actors)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment = MovieDetailsFragment().apply {
            arguments = Bundle().apply { putSerializable("MOVIE", movie) }
        }
    }
}