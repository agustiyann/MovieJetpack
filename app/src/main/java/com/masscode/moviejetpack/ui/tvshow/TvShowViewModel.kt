package com.masscode.moviejetpack.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.TvShow

class TvShowViewModel(private val movieRepository: Repository) : ViewModel() {

    fun getTvShowList(): LiveData<List<TvShow>> = movieRepository.getTvShow()

}