package com.masscode.moviejetpack.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.local.room.FavoriteDao

class LocalDataSource private constructor(private val favoriteDao: FavoriteDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoriteDao: FavoriteDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoriteDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, Movie> = favoriteDao.getMovies()

    fun getAllTvShows(): DataSource.Factory<Int, TvShow> = favoriteDao.getTvShows()

    fun getFavoriteMovies(): DataSource.Factory<Int, Movie> = favoriteDao.getFavoriteMovies()

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShow> = favoriteDao.getFavoriteTvShows()

    fun getMovieById(movieId: Int): LiveData<Movie> = favoriteDao.getMovieById(movieId)

    fun getTvShowById(tvShowId: Int): LiveData<TvShow> = favoriteDao.getTvShowById(tvShowId)

    fun insertAllMovies(movies: List<Movie>) = favoriteDao.insertAllMovie(movies)

    fun insertAllTvShow(tvShows: List<TvShow>) = favoriteDao.insertAllTvShow(tvShows)

    suspend fun setMovieFavorite(movie: Movie, newState: Boolean) {
        movie.isFavorite = newState
        favoriteDao.updateMovie(movie)
    }

    suspend fun setTvShowFavorite(tvShow: TvShow, newState: Boolean) {
        tvShow.isFavorite = newState
        favoriteDao.updateTvShow(tvShow)
    }

}