package com.masscode.moviejetpack.data.source.local

import androidx.lifecycle.LiveData
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.local.room.FavoriteDao

class LocalDataSource private constructor(private val favoriteDao: FavoriteDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoriteDao: FavoriteDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoriteDao)
    }

    fun getAllMovies(): LiveData<List<Movie>> = favoriteDao.getMovies()

    fun getAllTvShows(): LiveData<List<TvShow>> = favoriteDao.getTvShows()

    fun insertMovie(movie: Movie) = favoriteDao.insertMovie(movie)

    fun insertTvShow(tvShow: TvShow) = favoriteDao.insertTvShow(tvShow)

    fun deleteMovie(movie: Movie) = favoriteDao.deleteMovie(movie)

    fun deleteTvShow(tvShow: TvShow) = favoriteDao.deleteTvShow(tvShow)

}