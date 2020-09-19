package com.masscode.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.local.entity.Movie

interface DataSource {

    suspend fun getMovies(): LiveData<List<Movie>>

    fun getTvShow(): LiveData<List<TvShow>>

    fun getMovieById(movieId: Int): LiveData<Movie>

    fun getTvShowById(tvId: Int): LiveData<TvShow>

    fun getMovieLocal(): LiveData<PagedList<Movie>>

    suspend fun setMovieFavorite(movie: Movie, state: Boolean)

    fun getFavoriteMovies(): LiveData<PagedList<Movie>>

}