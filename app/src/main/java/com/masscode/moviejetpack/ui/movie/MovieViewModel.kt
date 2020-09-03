package com.masscode.moviejetpack.ui.movie

import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.Movie
import com.masscode.moviejetpack.utils.DummyData

class MovieViewModel : ViewModel() {

    fun getMovieList(): List<Movie> = DummyData.generateMovie()

}