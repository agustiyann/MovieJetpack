package com.masscode.moviejetpack.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.Movie

class MovieFavoriteViewModel(private val repository: Repository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<Movie>> = repository.getFavoriteMovies()

}