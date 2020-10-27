package com.masscode.moviejetpack.ui.detail

import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val repository: Repository) : ViewModel() {

    private val vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.IO)

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) = coroutineScope.launch {
        repository.setMovieFavorite(movie, newStatus)
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}