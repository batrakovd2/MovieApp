package com.example.movieapp.router

import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.main.FavoriteFragment
import com.example.movieapp.ui.main.MovieDetailsFragment
import com.example.movieapp.ui.main.MovieListFragment
import com.example.movieapp.ui.main.RatingFragment

class MainRouter(private val activity: AppCompatActivity) {
    fun openMovieList() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieListFragment())
            .commit()
    }

    fun openMovieDetailList(movie: Movie) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieDetailsFragment.newInstance())
            .addToBackStack("openMovieDetailList")
            .commit()
    }

    fun openFavoriteList() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, FavoriteFragment())
            .addToBackStack("openFavoriteList")
            .commit()
    }

    fun openRatingeList() {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, RatingFragment())
            .addToBackStack("openRatingeList")
            .commit()
    }


}