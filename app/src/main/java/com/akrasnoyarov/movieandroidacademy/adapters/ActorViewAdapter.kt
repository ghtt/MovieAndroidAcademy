package com.akrasnoyarov.movieandroidacademy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akrasnoyarov.movieandroidacademy.R
import com.akrasnoyarov.movieandroidacademy.model.Actor
import com.bumptech.glide.Glide

class ActorViewAdapter(private val actorsList: List<Actor>) :
    RecyclerView.Adapter<ActorViewAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_details_actors_item, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actorsList[position])
    }

    override fun getItemCount(): Int = actorsList.size

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(actor: Actor) {
            itemView.run {
                Glide.with(findViewById<ImageView>(R.id.actor_image_view))
                    .load(actor.imageUrl)
                    .centerCrop()
                    .into(findViewById<ImageView>(R.id.actor_image_view))

                findViewById<TextView>(R.id.actor_name_text_view).apply {
                    text = actor.name
                }
            }
        }
    }
}