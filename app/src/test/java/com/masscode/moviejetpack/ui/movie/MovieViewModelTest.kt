package com.masscode.moviejetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: Repository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovieList() {
        val dummyMovies = DummyData.generateMovieList()
        val movies = MutableLiveData<List<Movie>>()
        movies.value = dummyMovies

        `when`(movieRepository.getMovies()).thenReturn(movies)
        val movieList = viewModel.getMovieList().value
        verify(movieRepository).getMovies()
        assertNotNull(movieList)
        assertEquals(11, movieList?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}