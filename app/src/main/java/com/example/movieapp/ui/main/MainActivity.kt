package com.example.movieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, MainFragment.newInstance())
                .commitNow()
        }

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    addFragment(MainFragment.newInstance(), "MainFragment")
                }
                R.id.navigation_favorites -> {
                    addFragment(FavoriteFragment.newInstance(), "FavoriteFragment")
                }
                R.id.navigation_ratings -> {
                    addFragment(RatingFragment.newInstance(), "RatingFragment")
                }
            }
            true
        }


    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment, tag)
            .commit()
    }


}