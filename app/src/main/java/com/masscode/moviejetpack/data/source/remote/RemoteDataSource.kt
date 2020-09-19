package com.masscode.moviejetpack.data.source.remote

import android.util.Log
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

    suspend fun loadTvShows(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        withContext(Dispatchers.IO) {
            val tvShows = tmdbApi.api.getPopularTvShows(page = 1).tvShows
            callback.onTvShowReceived(tvShows)
            EspressoIdlingResource.decrement()
        }
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