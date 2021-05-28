package com.example.movieapp.viewmodel

import com.example.movieapp.model.Movie

sealed class AppState {
    data class Success(val movie: MutableList<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}