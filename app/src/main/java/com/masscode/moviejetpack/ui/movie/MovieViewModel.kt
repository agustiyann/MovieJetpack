package com.masscode.moviejetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.Movie

class MovieViewModel(private val movieRepository: Repository) : ViewModel() {

    fun getMovieList(): LiveData<List<Movie>> = movieRepository.getMovies()

}