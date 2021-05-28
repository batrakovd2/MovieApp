package com.example.movieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.router.MainRouter
import com.example.movieapp.router.RouterHolder

class MainActivity : AppCompatActivity(), RouterHolder {

    override val router = MainRouter(this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (savedInstanceState == null) {
            router.openMovieList()
        }

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    router.openMovieList()
                }
                R.id.navigation_favorites -> {
                    router.openFavoriteList()
                }
                R.id.navigation_ratings -> {
                    router.openRatingeList()
                }
            }
            true
        }


    }




}