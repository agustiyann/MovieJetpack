package com.masscode.moviejetpack.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.databinding.ItemTvshowListBinding

class TvShowFavoriteAdapter(private val showDetail: (TvShow?) -> Unit) :
    PagedListAdapter<TvShow, TvShowFavoriteAdapter.TvViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class TvViewHolder(private var binding: ItemTvshowListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: TvShow) {
            binding.tvShow = tvShows
            binding.executePendingBindings()

            with(binding.root) {
                setOnClickListener {
                    showDetail(tvShows)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFavoriteAdapter.TvViewHolder {
        val view: ItemTvshowListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tvshow_list,
            parent,
            false
        )
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowFavoriteAdapter.TvViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

}