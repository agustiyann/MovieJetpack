package com.masscode.moviejetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.local.entity.Movie
import kotlin.properties.Delegates

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _tvShow = MutableLiveData<TvShow>()
    val tvShow: LiveData<TvShow>
        get() = _tvShow

    var isMovie = false

    fun setMovie(movie: Movie) {
        isMovie = true
        _movie.postValue(movie)
    }

    fun setTvShow(tvShow: TvShow) {
        _tvShow.postValue(tvShow)
    }

//    fun addMovieToFavorite(movie: Movie) {
//        repository.addMovieFavorite(movie)
//    }
}