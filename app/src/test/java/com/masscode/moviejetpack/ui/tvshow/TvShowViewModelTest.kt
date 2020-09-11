package com.masscode.moviejetpack.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.masscode.moviejetpack.data.Repository
import com.masscode.moviejetpack.data.source.local.entity.TvShow
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<TvShow>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repository)
    }

    @Test
    fun getTvShowList() {
        val dummyTvShows = DummyData.generateTvShowList()
        val tvShows = MutableLiveData<List<TvShow>>()
        tvShows.value = dummyTvShows

        `when`(repository.getTvShow()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShowList().value
        verify(repository).getTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(11, tvShowEntities?.size)

        viewModel.getTvShowList().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}