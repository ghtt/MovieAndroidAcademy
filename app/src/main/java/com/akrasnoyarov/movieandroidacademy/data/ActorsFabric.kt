package com.akrasnoyarov.movieandroidacademy.data

import com.akrasnoyarov.movieandroidacademy.R

object ActorsFabric {
    var actorsList = mutableListOf<Actor>(
        Actor(R.drawable.robert, R.string.robet_downey),
        Actor(R.drawable.mark, R.string.mark_ruffalo),
        Actor(R.drawable.chris, R.string.chris_nevans),
        Actor(R.drawable.chris_hem, R.string.chris_hemsworth)
    )
}