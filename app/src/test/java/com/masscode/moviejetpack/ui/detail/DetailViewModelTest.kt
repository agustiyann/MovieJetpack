package com.masscode.moviejetpack.ui.detail

import com.masscode.moviejetpack.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val movieDummy = DummyData.generateMovieList()[0]
    private val tvShowDummy = DummyData.generateTvShowList()[0]

    private val movieId = movieDummy.id
    private val tvShowId = tvShowDummy.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getDetailMovie() {
        viewModel.setSelectedItem(movieId)
        val movieData = viewModel.getDetailMovie()
        assertNotNull(movieData)
        assertEquals(movieDummy.id, movieData.id)
        assertEquals(movieDummy.title, movieData.title)
        assertEquals(movieDummy.releaseDate, movieData.releaseDate)
        assertEquals(movieDummy.overview, movieData.overview)
        assertEquals(movieDummy.posterPath, movieData.posterPath)
        assertEquals(movieDummy.trailer, movieData.trailer)
    }

    @Test
    fun getDetailTvShow() {
        viewModel.setSelectedItem(tvShowId)
        val tvShowData = viewModel.getDetailTvShow()
        assertNotNull(tvShowData)
        assertEquals(tvShowDummy.id, tvShowData.id)
        assertEquals(tvShowDummy.name, tvShowData.name)
        assertEquals(tvShowDummy.firstAirDate, tvShowData.firstAirDate)
        assertEquals(tvShowDummy.overview, tvShowData.overview)
        assertEquals(tvShowDummy.posterPath, tvShowData.posterPath)
        assertEquals(tvShowDummy.trailer, tvShowData.trailer)
        assertEquals(tvShowDummy, tvShowData)
    }
}