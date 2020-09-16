package com.masscode.moviejetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM movie_entity")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM tv_entity")
    fun getTvShows(): LiveData<List<TvShow>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: TvShow)

    @Delete
    fun deleteMovie(movie: Movie)

    @Delete
    fun deleteTvShow(tvShow: TvShow)

}