package com.example.movieapp.model

import android.os.Handler
import android.os.Looper
import com.example.movieapp.R
import java.util.*
import java.util.concurrent.Executor
import kotlin.random.Random

class RepositoryImpl {

    private val mainThreadHandler = Handler(Looper.getMainLooper())

    fun getMovie(executor: Executor, callback: (result: RepositoryResult<List<Movie>>) -> Unit) {

        executor.execute{
            Thread.sleep(1000L)

            val isEverythingAllright = true//Random.nextBoolean()

            if (isEverythingAllright) {

                val result = listOf(
                    Movie(0, "Title 1", "Overview 1", Date(2020-1-1), "Drama", 100.0, 50, 0.5, R.drawable.ic_cardview),
                    Movie(1, "Title 2", "Overview 2", Date(2020-1-1), "Drama", 100.0, 50, 0.5, R.drawable.ic_cardview),
                    Movie(2, "Title 3", "Overview 3", Date(2020-1-1), "Drama", 100.0, 50, 0.5, R.drawable.ic_cardview),
                    Movie(3, "Title 4", "Overview 4", Date(2020-1-1), "Drama", 100.0, 50, 0.5, R.drawable.ic_cardview),
                    Movie(4, "Title 5", "Overview 5", Date(2020-1-1), "Drama", 100.0, 50, 0.5, R.drawable.ic_cardview),
                    Movie(5, "Title 6", "Overview 6", Date(2020-1-1), "Drama", 100.0, 50, 0.5, R.drawable.ic_cardview),
                    Movie(6, "Title 7", "Overview 7", Date(2020-1-1), "Drama", 100.0, 50, 0.5, R.drawable.ic_cardview),
                    Movie(7, "Title 8", "Overview 8", Date(2020-1-1), "Drama", 100.0, 50, 0.5, R.drawable.ic_cardview),
                    Movie(8, "Title 9", "Overview 9", Date(2020-1-1), "Drama", 100.0, 50, 0.5, R.drawable.ic_cardview),
                )

                mainThreadHandler.post {
                    callback(
                        Success(result)
                    )
                }
            } else {
                mainThreadHandler.post {
                    callback(
                        Error(RuntimeException("It's very very bad!!!"))
                    )
                }
            }
        }
    }
}

sealed class RepositoryResult<T>

data class Success<T>(val value: T) : RepositoryResult<T>()

data class Error<T>(val value: Throwable) : RepositoryResult<T>()