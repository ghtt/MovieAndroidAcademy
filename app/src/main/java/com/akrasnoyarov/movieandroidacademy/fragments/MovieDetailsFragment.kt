package com.akrasnoyarov.movieandroidacademy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.akrasnoyarov.movieandroidacademy.R
import com.akrasnoyarov.movieandroidacademy.api.RetrofitModule
import com.akrasnoyarov.movieandroidacademy.data.TheMovieDbRepository
import com.akrasnoyarov.movieandroidacademy.viewmodels.ViewModelFactory
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {
    private var actorsRecyclerView: RecyclerView? = null
    private var repository = TheMovieDbRepository()
    private val viewModel by lazy { ViewModelFactory.getInstance(repository) }
    private var progressBar: ProgressBar? = null
    private var movieImageView: ImageView? = null
    private var movieTitleTextView: TextView? = null
    private var movieRatingTextView: TextView? = null
    private var movieGenreTextView: TextView? = null
    private var movieReviewCountTextView: TextView? = null
    private var movieStoryLineTextView: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val movieId = arguments?.getInt("MOVIE")
        viewModel.loadMovieById(movieId!!)

        initUI(view)

        viewModel.loading.observe(viewLifecycleOwner) {
            progressBar?.isVisible = it
            movieImageView?.isInvisible = it
            movieTitleTextView?.isInvisible = it
            movieRatingTextView?.isInvisible = it
            movieGenreTextView?.isInvisible = it
            movieReviewCountTextView?.isInvisible = it
            movieStoryLineTextView?.isInvisible = it
        }
        viewModel.movie.observe(viewLifecycleOwner) {
            Glide.with(activity!!)
                .load(RetrofitModule.imageUrl.plus(it.posterPath!!))
                .into(movieImageView!!)

            movieRatingTextView.apply {
//                text = movie.pgAge.toString()
            }

            movieTitleTextView.apply {
                this?.text = it.title
            }

            movieGenreTextView.apply {
                this?.text = it.genres.let {
                    var genreString = ""
                    it?.forEach { item ->
                        genreString = genreString.plus(item?.name).plus(" ")
                    }
                    genreString
                }
            }

            movieReviewCountTextView.apply {
                this?.text = it.voteCount.toString()
            }

            movieStoryLineTextView.apply {
                this?.text = it.overview
            }
        }
        return view
    }

    private fun initUI(view: View) {
        progressBar = view.findViewById(R.id.details_progress_bar)
        movieImageView = view.findViewById(R.id.movie_image_view)
        movieTitleTextView = view.findViewById(R.id.movie_title_text_view)
        movieRatingTextView = view.findViewById(R.id.rating_text_view)
        movieGenreTextView = view.findViewById(R.id.movie_genre_text_view)
        movieReviewCountTextView = view.findViewById(R.id.review_count_text_view)
        movieStoryLineTextView = view.findViewById(R.id.storylineText)

    }
//        actorsRecyclerView = view.findViewById<RecyclerView>(R.id.actors_recycler_view)?.apply {
//            adapter = ActorViewAdapter(movie.)
//            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//        }


    companion object {
        fun newInstance(movieId: Int): MovieDetailsFragment = MovieDetailsFragment().apply {
            arguments = Bundle().apply { putInt("MOVIE", movieId) }
        }
    }
}