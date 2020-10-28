package com.masscode.moviejetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM movie_entity")
    fun getMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM tv_entity")
    fun getTvShows(): DataSource.Factory<Int, TvShow>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllMovie(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllTvShow(tvShow: List<TvShow>)

    @Transaction
    @Query("SELECT * FROM movie_entity WHERE id = :movieId")
    fun getMovieById(movieId: Int): LiveData<Movie>

    @Transaction
    @Query("SELECT * FROM tv_entity WHERE id = :tvShowId")
    fun getTvShowById(tvShowId: Int): LiveData<TvShow>

    @Update
    suspend fun updateMovie(movie: Movie)

    @Update
    suspend fun updateTvShow(tvShow: TvShow)

    @Query("SELECT * FROM movie_entity WHERE isFavorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM tv_entity WHERE isFavorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShow>

}