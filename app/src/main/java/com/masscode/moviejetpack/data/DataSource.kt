package com.masscode.moviejetpack.data

import androidx.lifecycle.LiveData
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.local.entity.Movie

interface DataSource {

    fun getMovies(): LiveData<List<Movie>>

    fun getTvShow(): LiveData<List<TvShow>>

    fun getMovieById(movieId: Int): LiveData<Movie>

    fun getTvShowById(tvId: Int): LiveData<TvShow>

    fun getMovieFavorite(): LiveData<List<Movie>>

}