package com.masscode.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.local.entity.Movie

interface DataSource {

    suspend fun getMovies(): LiveData<List<Movie>>

    suspend fun getTvShow(): LiveData<List<TvShow>>

    fun getMovieById(movieId: Int): LiveData<Movie>

    fun getTvShowById(tvId: Int): LiveData<TvShow>

    fun getMoviesLocal(): LiveData<PagedList<Movie>>

    fun getTvShowsLocal(): LiveData<PagedList<TvShow>>

    suspend fun setMovieFavorite(movie: Movie, state: Boolean)

    suspend fun setTvShowFavorite(tvShow: TvShow, state: Boolean)

    fun getFavoriteMovies(): LiveData<PagedList<Movie>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShow>>

}