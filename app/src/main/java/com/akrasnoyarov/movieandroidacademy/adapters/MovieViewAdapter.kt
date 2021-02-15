package com.akrasnoyarov.movieandroidacademy.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akrasnoyarov.movieandroidacademy.R
import com.akrasnoyarov.movieandroidacademy.model.Actor
import com.akrasnoyarov.movieandroidacademy.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MovieViewAdapter(var listener: OnMovieClickListener?) :
    RecyclerView.Adapter<MovieViewAdapter.MovieViewHolder>() {
    private var movies: List<Movie>? = null

    interface OnMovieClickListener {
        fun onMovieItemClicked(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies?.get(position))
    }

    override fun getItemCount(): Int = movies?.size ?: 0

    fun setMoviesList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View, listener: OnMovieClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        fun onBind(movie: Movie?) {
            itemView.run {
                val imageView = findViewById<ImageView>(R.id.movie_list_image_view).apply {
                    setOnClickListener {
                        listener?.onMovieItemClicked(movie!!)
                    }
                }
                Glide.with(itemView.context)
                    .load(movie?.imageUrl)
                    .centerCrop()
                    .into(imageView)

                findViewById<TextView>(R.id.raiting_text_view).apply {
                    text = movie?.pgAge.toString()
                }

                findViewById<TextView>(R.id.movie_title_text_view).apply {
                    text = movie?.title.toString()
                }

                findViewById<TextView>(R.id.duration_text_view).apply {
                    text = movie?.runningTime.toString()
                }

                findViewById<TextView>(R.id.movie_genre_text_view).apply {
                    text = movie?.genres.let {
                        var genreString = ""
                        it?.forEach { item ->
                            genreString = genreString.plus(item.name).plus(" ")
                        }
                        genreString
                    }
                }

                findViewById<TextView>(R.id.review_count_text_view).apply {
                    text = movie?.reviewCount.toString()
                }
            }
        }
    }
}