package com.masscode.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource
import com.masscode.moviejetpack.data.source.local.entity.Movie

class Repository private constructor(private val remoteDataSource: RemoteDataSource) :
    DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData)
            }
    }

    override fun getMovies(): LiveData<List<Movie>> {
        val movieResult = MutableLiveData<List<Movie>>()
        remoteDataSource.loadMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onMovieReceived(movieList: List<Movie>) {

                movieResult.postValue(movieList)
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
        val mMovie = MutableLiveData<Movie>()
        remoteDataSource.getMovieById(movieId, object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onDetailReceived(movie: Movie) {
                mMovie.postValue(movie)
            }
        })

        return mMovie
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
}