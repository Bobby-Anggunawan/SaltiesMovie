package com.bangkit.saltiesmovie.core.datalayer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    val backdrop_path: String,
    val budget: Int,
    val homepage: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val status: String,
    val tagline: String,
    val vote_average: Double,
    val vote_count: Int
)