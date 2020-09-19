package com.masscode.moviejetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {

    val movieId = MutableLiveData<Int>()
    var isMovie = false

    fun setSelectedMovie(movieId: Int) {
        isMovie = true
        this.movieId.value = movieId
    }

    val moviee: LiveData<Movie> = Transformations.switchMap(movieId) { mId ->
        repository.getMovieById(mId)
    }

    private val _tvShow = MutableLiveData<TvShow>()
    val tvShow: LiveData<TvShow>
        get() = _tvShow

    private val vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.IO)

    fun setTvShow(tvShow: TvShow) {
        _tvShow.postValue(tvShow)
    }

    fun setMovieFavorite() {
        val movie = moviee.value
        val newState = !movie!!.isFavorite
        coroutineScope.launch {
            repository.setMovieFavorite(movie, newState)
        }
    }
}