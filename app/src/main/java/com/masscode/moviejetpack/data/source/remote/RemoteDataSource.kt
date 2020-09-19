package com.masscode.moviejetpack.data.source.remote

import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.remote.network.TMDBApi
import com.masscode.moviejetpack.data.source.remote.response.MovieResponse
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.remote.response.TvShowResponse
import com.masscode.moviejetpack.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val tmdbApi: TMDBApi) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(api: TMDBApi): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource(api)
        }
    }

    suspend fun loadMovies(callback: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        withContext(Dispatchers.IO) {
            val movies = tmdbApi.api.getPopularMovies(page = 1).movies
            callback.onMovieReceived(movies)
            EspressoIdlingResource.decrement()
        }
    }

    fun loadTvShows(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        tmdbApi.api.getPopularTvShows(page = 1).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onTvShowReceived(responseBody.tvShows)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getMovieById(id: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        tmdbApi.api.getDetailMovie(movieId = id).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onDetailReceived(responseBody)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getTvShowById(id: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        tmdbApi.api.getDetailTv(tvId = id).enqueue(object : Callback<TvShow> {
            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onDetailReceived(responseBody)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    interface LoadMovieCallback {
        fun onMovieReceived(movieList: List<Movie>)
    }

    interface LoadTvShowCallback {
        fun onTvShowReceived(tvShowList: List<TvShow>)
    }

    interface LoadMovieDetailCallback {
        fun onDetailReceived(movie: Movie)
    }

    interface LoadTvShowDetailCallback {
        fun onDetailReceived(tvShow: TvShow)
    }

}