package com.example.movieapp.model

class RepositoryImpl: Repository {
    override fun getMovieFromServer(): Movie {
        return Movie()
    }

    override fun getMovieFromLocalStorage(): MutableList<Movie> {
        val list: MutableList<Movie> = mutableListOf(Movie(0,
            "Title1",
            "Overview1",
            "01-01-2020",
            "Dram",
            100.0,
            10))
        list.add(Movie(0, "Title2", "Overview2", "01-01-2020", "Dram", 100.0, 10))

        return list
    }
}