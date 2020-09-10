package com.masscode.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource
import com.masscode.moviejetpack.data.source.remote.response.Movies

class MovieRespository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRespository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRespository =
            instance ?: synchronized(this) {
                instance ?: MovieRespository(remoteData)
            }
    }

    override fun getMovies(): LiveData<List<Movies>> {
        val movieResult = MutableLiveData<List<Movies>>()
        remoteDataSource.loadMovies(object : RemoteDataSource.LoadMovieCallback {
            override fun onMovieReceived(movieList: List<Movies>) {

                movieResult.postValue(movieList)
            }
        })

        return movieResult
    }
}