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
    val tvId = MutableLiveData<Int>()
    var isMovie = false

    fun setSelectedMovie(movieId: Int) {
        isMovie = true
        this.movieId.value = movieId
    }

    fun setSelectedTvId(tvId: Int) {
        this.tvId.value = tvId
    }

    val movie: LiveData<Movie> = Transformations.switchMap(movieId) { mId ->
        repository.getMovieById(mId)
    }

    val tvShow: LiveData<TvShow> = Transformations.switchMap(tvId) { mId ->
        repository.getTvShowById(mId)
    }

    private val vmJob = Job()
    private val coroutineScope = CoroutineScope(vmJob + Dispatchers.IO)

    fun setMovieFavorite() {
        val movie = movie.value
        val newState = !movie!!.isFavorite
        coroutineScope.launch {
            repository.setMovieFavorite(movie, newState)
        }
    }

    fun setTvShowFavorite() {
        val tvShow = tvShow.value
        val newState = !tvShow!!.isFavorite
        coroutineScope.launch {
            repository.setTvShowFavorite(tvShow, newState)
        }
    }
}