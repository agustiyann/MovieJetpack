package com.masscode.moviejetpack.data.source.remote.network

import com.masscode.moviejetpack.data.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "a6beac03fb8ef024b93e511757777e5c",
        @Query("page") page: Int
    ): Call<MovieResponse>

//    @GET("tv/popular")
//    fun getPopularTvShows(
//        @Query("api_key") apiKey: String = "a6beac03fb8ef024b93e511757777e5c",
//        @Query("page") page: Int
//    ): Call<TvShowsResponse>

}