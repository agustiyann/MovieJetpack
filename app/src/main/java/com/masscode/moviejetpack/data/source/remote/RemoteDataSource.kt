package com.masscode.moviejetpack.data.source.remote

import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.remote.network.TMDBApi
import com.masscode.moviejetpack.data.source.remote.response.MovieResponse
import com.masscode.moviejetpack.data.source.remote.response.Movies
import com.masscode.moviejetpack.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val tmdbApi: TMDBApi) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(api: TMDBApi): RemoteDataSource = instance?: synchronized(this) {
            instance ?: RemoteDataSource(api)
        }
    }

    fun loadMovies(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        tmdbApi.api.getPopularMovies(page = 1).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody!=null) {
                        callback.onMovieReceived(responseBody.movies)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    interface LoadMovieCallback {
        fun onMovieReceived(movieList: List<Movies>)
    }

}