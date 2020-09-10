package com.masscode.moviejetpack.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.MovieRespository
import com.masscode.moviejetpack.data.source.remote.response.Movies

class MovieViewModel(private val movieRespository: MovieRespository) : ViewModel() {

    fun getMovieList(): LiveData<List<Movies>> = movieRespository.getMovies()

}