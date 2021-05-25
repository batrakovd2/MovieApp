package com.example.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.model.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var items: MutableList<Movie> = mutableListOf(Movie(0,"","","","", 0.0,0))

    public fun addItems(toAdd: MutableList<Movie>) = items.addAll(toAdd)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(root)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = items[position]
        holder.title.setText(item.title)
        Glide.with(holder.image.context).load(item.posterImage).into(holder.image)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    public class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.movie_item_title)
        var image: ImageView = itemView.findViewById(R.id.movie_item_image)
    }

}