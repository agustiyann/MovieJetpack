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

    private val _movies: LiveData<PagedList<Movie>>
    val movies: LiveData<PagedList<Movie>>
        get() = _movies

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

        _movies = repository.getMovieLocal()
    }

}