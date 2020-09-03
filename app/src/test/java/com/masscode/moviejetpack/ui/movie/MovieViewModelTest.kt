package com.masscode.moviejetpack.ui.movie

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovieList() {
        val movieList = viewModel.getMovieList()
        assertNotNull(movieList)
        assertEquals(11, movieList.size)
    }
}