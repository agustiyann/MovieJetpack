package com.masscode.moviejetpack.ui.detail

import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailTvShowViewModel(private val repository: Repository) : ViewModel() {

    private val vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.IO)

    fun setFavoriteTvShow(tvShow: TvShow, newStatus: Boolean) = coroutineScope.launch {
        repository.setTvShowFavorite(tvShow, newStatus)
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}