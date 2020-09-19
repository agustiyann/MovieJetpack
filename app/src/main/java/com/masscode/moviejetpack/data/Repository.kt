package com.masscode.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.masscode.moviejetpack.data.source.local.LocalDataSource
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource
import com.masscode.moviejetpack.data.source.local.entity.Movie

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData, localData)
            }
    }

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

    override fun getTvShow(): LiveData<List<TvShow>> {
        val tvResult = MutableLiveData<List<TvShow>>()
        remoteDataSource.loadTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onTvShowReceived(tvShowList: List<TvShow>) {
                tvResult.postValue(tvShowList)
            }
        })

        return tvResult
    }

    override fun getMovieById(movieId: Int): LiveData<Movie> {
        return localDataSource.getMovieById(movieId)
    }

    override fun getTvShowById(tvId: Int): LiveData<TvShow> {
        val mTvShow = MutableLiveData<TvShow>()
        remoteDataSource.getTvShowById(tvId, object : RemoteDataSource.LoadTvShowDetailCallback {
            override fun onDetailReceived(tvShow: TvShow) {
                mTvShow.postValue(tvShow)
            }
        })

        return mTvShow
    }

    override fun getMovieLocal(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
    }

    override suspend fun setMovieFavorite(movie: Movie, state: Boolean) {
        localDataSource.setMovieFavorite(movie, state)
    }

    override fun getFavoriteMovies(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }


}