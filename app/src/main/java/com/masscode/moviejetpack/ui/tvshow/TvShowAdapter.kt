package com.masscode.moviejetpack.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.databinding.ItemTvshowListBinding

class TvShowAdapter(private val showDetail: (Int?, String?) -> Unit) :
    ListAdapter<TvShow, TvShowAdapter.TvViewHolder>(DiffCallback) {

    inner class TvViewHolder(private var binding: ItemTvshowListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: TvShow?) {
            binding.tvShow = tvShows
            binding.executePendingBindings()

            with(binding.root) {
                setOnClickListener {
                    showDetail(tvShows?.id, "tv show")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.TvViewHolder {
        val view: ItemTvshowListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tvshow_list,
            parent,
            false
        )
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.id == newItem.id
        }

    }
}