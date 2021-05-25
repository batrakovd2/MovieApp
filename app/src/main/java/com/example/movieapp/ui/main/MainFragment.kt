package com.example.movieapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.viewmodel.AppState
import com.example.movieapp.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var movieAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movieslist = binding.moviesList
        movieAdapter = MoviesAdapter()
        movieslist.adapter = movieAdapter
        movieslist.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getMovieFromLocalSource()
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                movieAdapter.addItems(appState.movie)
                movieAdapter.notifyDataSetChanged()
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {

            }

        }
    }

    private fun setData(movieData: Movie) {

    }

}