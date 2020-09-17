package com.masscode.moviejetpack.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.databinding.ItemMovieListBinding

class MovieAdapter(private val showDetail: (Movie?, String?) -> Unit) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffCallback) {

    inner class MovieViewHolder(private var binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()

            with(binding.root) {
                setOnClickListener {
                    showDetail(movie, "movie")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: ItemMovieListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_list,
            parent,
            false
        )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

    }
}