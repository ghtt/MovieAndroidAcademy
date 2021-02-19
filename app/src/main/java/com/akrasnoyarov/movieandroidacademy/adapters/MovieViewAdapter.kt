package com.akrasnoyarov.movieandroidacademy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akrasnoyarov.movieandroidacademy.R
import com.akrasnoyarov.movieandroidacademy.api.ResultsItem
import com.akrasnoyarov.movieandroidacademy.api.RetrofitModule
import com.bumptech.glide.Glide

class MovieViewAdapter(
    private var listener: OnMovieClickListener?
) : ListAdapter<ResultsItem, MovieViewAdapter.MovieViewHolder>(MoviesCallback) {

    interface OnMovieClickListener {
        fun onMovieItemClicked(movieId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(getItem(position), listener)
    }

    class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun onBind(movie: ResultsItem?, listener: OnMovieClickListener?) {
            setMovieImage(movie, listener)
            setMovieRating(movie)
            setMovieTitle(movie)
            setMovieDuration(movie)
            setMovieGenres(movie)
            setMovieReviewCount(movie)
        }

        private fun setMovieReviewCount(movie: ResultsItem?) {
            itemView.findViewById<TextView>(R.id.review_count_text_view).apply {
                text = movie?.voteCount.toString()
            }
        }

        private fun setMovieGenres(movie: ResultsItem?) {
//            itemView.findViewById<TextView>(R.id.movie_genre_text_view).apply {
//                text = movie.genreIds.let {
//                    var genreString = ""
//                    it?.forEach { item ->
//                        genreString = genreString.plus(item?.name).plus(" ")
//                    }
//                    genreString
//                }
//            }
        }

        private fun setMovieDuration(movie: ResultsItem?) {
            itemView.findViewById<TextView>(R.id.duration_text_view).apply {
                //text = movie.
            }
        }

        private fun setMovieTitle(movie: ResultsItem?) {
            itemView.findViewById<TextView>(R.id.movie_title_text_view).apply {
                text = movie?.title.toString()
            }
        }

        private fun setMovieRating(movie: ResultsItem?) {
            itemView.findViewById<TextView>(R.id.raiting_text_view).apply {
                //text = movie?.pgAge.toString()
            }
        }

        private fun setMovieImage(movie: ResultsItem?, listener: OnMovieClickListener?) {
            val imageView = itemView.findViewById<ImageView>(R.id.movie_list_image_view).apply {
                setOnClickListener {
                    listener?.onMovieItemClicked(movie?.id!!)
                }
            }
            Glide.with(itemView.context)
                .load(RetrofitModule.imageUrl.plus(movie?.posterPath!!))
                .centerCrop()
                .fitCenter()
                .into(imageView)
        }
    }

    companion object {
        private var MoviesCallback = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem == newItem
        }
    }
}