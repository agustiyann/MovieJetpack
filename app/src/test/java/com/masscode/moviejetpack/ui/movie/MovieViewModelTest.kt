package com.masscode.moviejetpack.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ObsoleteCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<Movie>>

    @Mock
    private lateinit var pagedList: PagedList<Movie>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = MovieViewModel(movieRepository)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun getMovieList() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(20)
        val movies = MutableLiveData<PagedList<Movie>>()
        movies.value = dummyMovies

        `when`(movieRepository.getMoviesLocal()).thenReturn(movies)
        val movieEntities = viewModel.getMovieList().value
        verify(movieRepository).getMoviesLocal()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}