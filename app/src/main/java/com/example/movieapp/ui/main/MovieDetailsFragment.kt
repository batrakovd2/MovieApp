package com.example.movieapp.ui.main

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.movieapp.databinding.MovieDetailFragmentBinding
import com.example.movieapp.model.RepositoryImpl
import com.example.movieapp.viewmodel.MovieDetailViewModel
import com.example.movieapp.viewmodel.viewBinding
import androidx.fragment.app.viewModels

class MovieDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private val viewBinding: MovieDetailFragmentBinding by viewBinding(
        MovieDetailFragmentBinding::bind
    )

    private val viewModel: MovieDetailViewModel by viewModels {
        MovieDetailsViewModelFactory(requireActivity().application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.fetchMovie()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.retry.setOnClickListener {
            viewModel.fetchMovie()
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            val error = it ?: return@observe

            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()

            with(viewBinding) {
                retry.visibility = View.VISIBLE
                movieTitle.visibility = View.GONE
                dateRelease.visibility = View.VISIBLE
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            viewBinding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.movieLiveData.observe(viewLifecycleOwner) {
            val movieInfo = it ?: return@observe

            with(viewBinding) {
                retry.visibility = View.GONE

                movieTitle.apply {
                    visibility = View.VISIBLE
                    text = movieInfo.title
                }
                dateRelease.apply {
                    visibility = View.VISIBLE
                    text = movieInfo.releaseDate.toString()
                }
            }
        }
    }


    class MovieDetailsViewModelFactory(private val aplication: Application) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MovieDetailViewModel(aplication, RepositoryImpl()) as T
    }

}