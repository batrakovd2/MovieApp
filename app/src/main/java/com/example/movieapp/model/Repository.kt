package com.example.movieapp.model

interface Repository {
    fun getMovieFromServer(): Movie
    fun getMovieFromLocalStorage(): List<Movie>
}