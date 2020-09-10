package com.masscode.moviejetpack.di

import com.masscode.moviejetpack.data.MovieRespository
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource
import com.masscode.moviejetpack.data.source.remote.network.TMDBApi

object Injection {

    fun provideRepository(): MovieRespository {
        val api = TMDBApi

        val remoteDataSource = RemoteDataSource.getInstance(api)

        return MovieRespository.getInstance(remoteDataSource)
    }
}