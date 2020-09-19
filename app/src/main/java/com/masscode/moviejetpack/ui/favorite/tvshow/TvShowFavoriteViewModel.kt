package com.masscode.moviejetpack.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.TvShow

class TvShowFavoriteViewModel(private val repository: Repository) : ViewModel() {

    fun getFavoriteTvShows(): LiveData<PagedList<TvShow>> = repository.getFavoriteTvShows()

}