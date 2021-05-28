package com.example.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.model.RepositoryImpl
import com.example.movieapp.model.Success
import java.util.*
import java.util.concurrent.Executors

class MovieListViewModel(
    private val app: Application,
    private val repository: RepositoryImpl
) : ViewModel()  {

    private val executor = Executors.newSingleThreadExecutor()

    private val _loadingLiveData = MutableLiveData(false)

    private val _errorLiveData = MutableLiveData<String?>()

    private val _movieLiveData = MutableLiveData<List<Movie>>()

    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    val errorLiveData: LiveData<String?> = _errorLiveData

    val movieLiveData: LiveData<List<Movie>> = _movieLiveData

    fun fetchMovie() {

        _loadingLiveData.value = true

        repository.getMovie(executor) {
            when (it) {
                is Success -> {

                    val result: List<Movie> = it.value
                    _movieLiveData.value = result
                }

                is Error -> {
                    _errorLiveData.value = app.getString(R.string.error)
                }
            }

            _loadingLiveData.value = false
        }
    }

}