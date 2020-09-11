package com.masscode.moviejetpack.data

import androidx.lifecycle.LiveData
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource

class FakeRepository (private val remoteDataSource: RemoteDataSource) : DataSource {
    override fun getMovies(): LiveData<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getTvShow(): LiveData<List<TvShow>> {
        TODO("Not yet implemented")
    }

    override fun getMovieById(movieId: Int): LiveData<Movie> {
        TODO("Not yet implemented")
    }

    override fun getTvShowById(tvId: Int): LiveData<TvShow> {
        TODO("Not yet implemented")
    }
}