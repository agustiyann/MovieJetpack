package com.masscode.moviejetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository) : ViewModel() {

    private val _movies: LiveData<List<Movie>>
    val movies: LiveData<List<Movie>>
        get() = _movies

    val vmJob = Job()
    val coroutineScope = CoroutineScope(vmJob + Dispatchers.IO)

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