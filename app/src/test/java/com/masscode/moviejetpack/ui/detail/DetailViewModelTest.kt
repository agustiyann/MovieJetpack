package com.masscode.moviejetpack.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val movieDummy = DummyData.generateMovieList()[0]
    private val tvShowDummy = DummyData.generateTvShowList()[0]

    private val movieId = movieDummy.id
    private val tvShowId = tvShowDummy.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<Movie>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShow>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
        viewModel.setSelectedMovie(movieId!!)
        viewModel.setSelectedTvId(tvShowId!!)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Movie>()
        movie.value = movieDummy

        `when`(repository.getMovieById(movieId!!)).thenReturn(movie)
        viewModel.movie.observeForever(movieObserver)
        verify(movieObserver).onChanged(movieDummy)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<TvShow>()
        tvShow.value = tvShowDummy

        `when`(repository.getTvShowById(tvShowId!!)).thenReturn(tvShow)
        viewModel.tvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShowDummy)
    }
}