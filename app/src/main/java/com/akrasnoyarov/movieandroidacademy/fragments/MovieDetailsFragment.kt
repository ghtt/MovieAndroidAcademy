package com.akrasnoyarov.movieandroidacademy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akrasnoyarov.movieandroidacademy.R
import com.akrasnoyarov.movieandroidacademy.adapters.ActorViewAdapter
import com.akrasnoyarov.movieandroidacademy.data.Actor
import com.akrasnoyarov.movieandroidacademy.data.ActorsFabric

class MovieDetailsFragment : Fragment() {
    private var actorsRecyclerView: RecyclerView? = null
    private var actorsAdapter: ActorViewAdapter? = null

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
        actorsAdapter = ActorViewAdapter(ActorsFabric.actorsList)

        actorsRecyclerView = view.findViewById<RecyclerView>(R.id.actors_recycler_view)?.apply {
            adapter = actorsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }
}