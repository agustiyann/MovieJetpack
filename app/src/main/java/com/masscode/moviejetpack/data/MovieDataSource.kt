package com.masscode.moviejetpack.data

import androidx.lifecycle.LiveData
import com.masscode.moviejetpack.data.source.remote.response.Movies

interface MovieDataSource {
    fun getMovies(): LiveData<List<Movies>>

}