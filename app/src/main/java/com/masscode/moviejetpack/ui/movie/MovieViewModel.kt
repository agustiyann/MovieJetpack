package com.masscode.moviejetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository) : ViewModel() {

    fun getMovieList(): LiveData<PagedList<Movie>> = repository.getMoviesLocal()

    private val vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
        coroutineScope.launch {
            try {
                repository.getMovies()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

}