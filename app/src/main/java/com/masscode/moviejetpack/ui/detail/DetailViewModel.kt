package com.masscode.moviejetpack.ui.detail

import androidx.lifecycle.ViewModel
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.remote.response.Movies
import com.masscode.moviejetpack.utils.DummyData
import kotlin.properties.Delegates

class DetailViewModel : ViewModel() {

    private var id by Delegates.notNull<Int>()
    var isMovie = false

    fun setSelectedItem(itemId: Int) {
        this.id = itemId
    }

    fun getDetailMovie(): Movies {
        lateinit var movie: Movies
        val movieEntities = DummyData.generateMovieList()
        for (movieEntity in movieEntities) {
            if (movieEntity.id == id) {
                movie = movieEntity
            }
        }
        isMovie = true
        return movie
    }

    fun getDetailTvShow(): TvShow {
        lateinit var tvShow: TvShow
        val tvShowEntities = DummyData.generateTvShowList()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.id == id) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }

}