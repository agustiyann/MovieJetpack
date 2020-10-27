package com.masscode.moviejetpack.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.utils.DummyData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val movieDummy = DummyData.generateMovieList()[0]
    private var isFavorite = movieDummy.isFavorite

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(repository)
    }

    @Test
    fun setFavoriteMovie() {
        isFavorite = !isFavorite
        viewModel.setFavoriteMovie(movieDummy, isFavorite)
    }
}