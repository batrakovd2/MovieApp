package com.example.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.Movie

typealias ItemClicked = (movie: Movie) -> Unit

class MovieListAdapter(private val itemClicked: ItemClicked? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Movie>()

    fun setData(dataToSet: List<Movie>) {
        data.apply {
            clear()
            addAll(dataToSet)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie, parent, false
            )
        )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        (holder as? MovieViewHolder)?.let { movieHolder ->
            movieHolder.movieTitle.text = item.title
            movieHolder.moviePoster.setImageResource(item.posterImage)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.findViewById(R.id.movie_item_title)
        val moviePoster: ImageView = itemView.findViewById(R.id.movie_item_image)

        init {
            itemView.setOnClickListener {
                itemClicked?.invoke(data[adapterPosition])
            }
        }

    }

}