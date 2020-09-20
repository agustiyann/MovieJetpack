package com.masscode.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.masscode.moviejetpack.data.source.local.LocalDataSource
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource

class FakeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DataSource {

    override suspend fun getMovies(): LiveData<List<Movie>> {
        val movieResult = MutableLiveData<List<Movie>>()
        remoteDataSource.loadMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onMovieReceived(movieList: List<Movie>) {
                movieResult.postValue(movieList)
                localDataSource.insertAllMovies(movieList)
            }
        })

        return movieResult
    }

    override suspend fun getTvShow(): LiveData<List<TvShow>> {
        val tvResult = MutableLiveData<List<TvShow>>()
        remoteDataSource.loadTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onTvShowReceived(tvShowList: List<TvShow>) {
                tvResult.postValue(tvShowList)
                localDataSource.insertAllTvShow(tvShowList)
            }
        })

        return tvResult
    }

    override fun getMovieById(movieId: Int): LiveData<Movie> {
        return localDataSource.getMovieById(movieId)
    }

    override fun getTvShowById(tvId: Int): LiveData<TvShow> {
        return localDataSource.getTvShowById(tvId)
    }

    override fun getMoviesLocal(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
    }

    override fun getTvShowsLocal(): LiveData<PagedList<TvShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
    }

    override suspend fun setMovieFavorite(movie: Movie, state: Boolean) {
        localDataSource.setMovieFavorite(movie, state)
    }

    override suspend fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        localDataSource.setTvShowFavorite(tvShow, state)
    }

    override fun getFavoriteMovies(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }
}