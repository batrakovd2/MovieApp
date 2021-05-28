package com.example.movieapp.viewmodel

import android.app.Application
import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.R
import com.example.movieapp.model.RepositoryImpl
import com.example.movieapp.model.Success
import java.util.*
import java.util.concurrent.Executors

class MovieDetailViewModel(
    private val app: Application,
    private val repository: RepositoryImpl
) : ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()

    private val _loadingLiveData = MutableLiveData(false)

    private val _errorLiveData = MutableLiveData<String?>()

    private val _movieLiveData = MutableLiveData<MovieInfo?>()

    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    val errorLiveData: LiveData<String?> = _errorLiveData

    val movieLiveData: LiveData<MovieInfo?> = _movieLiveData

    fun fetchMovie() {

        _loadingLiveData.value = true

        repository.getMovie(executor) {
            when (it) {
                is Success -> {

                    val result = it.value

                    _movieLiveData.value =
                        MovieInfo("City 17", Date(2020-1-1), R.drawable.ic_cardview)

                    _errorLiveData.value = null
                }

                is Error -> {
                    _errorLiveData.value = app.getString(R.string.error)
                    _movieLiveData.value = null
                }
            }

            _loadingLiveData.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        executor.shutdown()
    }

    data class MovieInfo(
        val title: String,
        val releaseDate: Date,
        @DrawableRes val posterImage: Int
    )


}