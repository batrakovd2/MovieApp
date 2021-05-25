package com.example.movieapp.model

data class Movie(
    val id: Int = 0,
    val title: String = "Унесенные ветром",
    val overview: String = "Описание",
    val releaseDate: String = "1980-05-01",
    val genre: String = "Драма",
    val popularity: Double = 6.7,
    val voteCount: Int = 1000,
    val voteAverage: Double = 97.0,
    val posterImage: String = "https://festagent.com/system/tilda/tild6665-3734-4462-b163-623335653163__4b98e300bbffcc3efa7c.jpg"
)