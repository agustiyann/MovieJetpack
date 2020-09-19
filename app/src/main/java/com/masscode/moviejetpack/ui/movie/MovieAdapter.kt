package com.masscode.moviejetpack.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.databinding.ItemMovieListBinding

class MovieAdapter(private val showDetail: (Int?, String?) -> Unit) :
    PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MovieViewHolder(private var binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()

            with(binding.root) {
                setOnClickListener {
                    showDetail(movie.id, "movie")
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
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}