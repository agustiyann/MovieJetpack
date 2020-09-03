package com.masscode.moviejetpack.ui.tvshow

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShowList() {
        val tvShowEntities = viewModel.getTvShowList()
        assertNotNull(tvShowEntities)
        assertEquals(11, tvShowEntities.size)
    }
}