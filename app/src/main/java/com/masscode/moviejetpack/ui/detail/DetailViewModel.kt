package com.masscode.moviejetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.local.entity.Movie
import kotlin.properties.Delegates

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private var movieId by Delegates.notNull<Int>()
    private var tvShowId by Delegates.notNull<Int>()
    var isMovie = false

    fun setSelectedMovie(id: Int) {
        this.movieId = id
    }

    fun setSelectedTvShow(id: Int) {
        this.tvShowId = id
    }

    fun getDetailMovie(): LiveData<Movie> {
        isMovie = true
        return repository.getMovieById(movieId)
    }

    fun getDetailTvShow(): LiveData<TvShow> = repository.getTvShowById(tvShowId)
}