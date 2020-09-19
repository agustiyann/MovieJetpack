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

    fun getAllMovies(): LiveData<List<Movie>> = favoriteDao.getMovies()

    fun getFavoriteMovies(): DataSource.Factory<Int, Movie> = favoriteDao.getFavoriteMovies()

    fun getAllTvShows(): LiveData<List<TvShow>> = favoriteDao.getTvShows()

    fun getMovieById(movieId: Int): LiveData<Movie> = favoriteDao.getMovieById(movieId)

    fun insertAllMovies(movies: List<Movie>) = favoriteDao.insertAllMovie(movies)



    suspend fun setMovieFavorite(movie: Movie, newState: Boolean) {
        movie.isFavorite = newState
        favoriteDao.updateMovie(movie)
    }

}