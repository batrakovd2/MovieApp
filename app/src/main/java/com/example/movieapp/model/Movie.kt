package com.example.movieapp.model

import java.util.*

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: Date,
    val genre: String,
    val popularity: Double,
    val voteCount: Int,
    val voteAverage: Double,
    val posterImage: Int
)