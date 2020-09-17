package com.masscode.moviejetpack.di

import android.content.Context
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.LocalDataSource
import com.masscode.moviejetpack.data.source.local.room.FavoriteDb
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource
import com.masscode.moviejetpack.data.source.remote.network.TMDBApi

object Injection {

    fun provideRepository(context: Context): Repository {
        val api = TMDBApi
        val database = FavoriteDb.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.favoriteDao())

        val remoteDataSource = RemoteDataSource.getInstance(api)

        return Repository.getInstance(remoteDataSource, localDataSource)
    }
}