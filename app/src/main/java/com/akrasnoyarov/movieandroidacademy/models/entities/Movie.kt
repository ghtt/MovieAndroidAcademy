package com.akrasnoyarov.movieandroidacademy.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val title: String?,
    val imageUrl: String?,
    val rating: Int?,
    val duration: Int?,
    val reviewCount: Int?,
    val overview: String? = null
)