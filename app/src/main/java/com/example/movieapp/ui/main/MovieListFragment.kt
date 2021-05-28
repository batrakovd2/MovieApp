package com.example.movieapp.ui.main

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.model.RepositoryImpl
import com.example.movieapp.router.RouterHolder
import com.example.movieapp.viewmodel.MovieListViewModel
import com.example.movieapp.viewmodel.viewBinding


class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val adapter = MovieListAdapter {
        (activity as? RouterHolder)?.router?.openMovieDetailList(it)
    }

    private val viewBinding: FragmentMovieListBinding by viewBinding(
        FragmentMovieListBinding::bind
    )

    private val viewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(requireActivity().application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null) {
            viewModel.fetchMovie()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.movieList.adapter = adapter

        viewBinding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchMovie()
            viewBinding.swipeRefresh.isRefreshing = false
        }

        viewModel.movieLiveData.observe(viewLifecycleOwner) {
            adapter.apply{
                setData(it)
                notifyDataSetChanged()
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            viewBinding.progress.visibility = if(it) View.VISIBLE else View.GONE
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    class MovieListViewModelFactory(private val aplication: Application) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MovieListViewModel(aplication, RepositoryImpl()) as T
    }

}