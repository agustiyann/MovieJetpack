package com.masscode.moviejetpack.di

import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource
import com.masscode.moviejetpack.data.source.remote.network.TMDBApi

object Injection {

    fun provideRepository(): Repository {
        val api = TMDBApi

        val remoteDataSource = RemoteDataSource.getInstance(api)

        return Repository.getInstance(remoteDataSource)
    }
}