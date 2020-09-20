package com.masscode.moviejetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.masscode.moviejetpack.data.source.local.LocalDataSource
import com.masscode.moviejetpack.data.source.local.entity.Movie
import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource
import com.masscode.moviejetpack.utils.DummyData
import com.masscode.moviejetpack.utils.LiveDataTestUtil
import com.masscode.moviejetpack.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val repository = FakeRepository(remote, local)

    private val movieResponses = DummyData.generateMovieList()
    private val tvShowResponses = DummyData.generateTvShowList()
    private val movieDummy = DummyData.generateMovieList()[0]
    private val tvShowDummy = DummyData.generateTvShowList()[0]
    private val movieId = movieDummy.id
    private val tvShowId = tvShowDummy.id

    @Test
    fun getAllMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        repository.getMoviesLocal()

        val movieEntities = PagedListUtil.mockPagedList(DummyData.generateMovieList())
        verify(local).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShow>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        repository.getTvShowsLocal()

        val tvShowEntities = PagedListUtil.mockPagedList(DummyData.generateTvShowList())
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<Movie>()
        dummyMovie.value = movieDummy
        `when`(local.getMovieById(movieId!!)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(repository.getMovieById(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieEntity)
        assertEquals(movieDummy.title, movieEntity.title)
    }

    @Test
    fun getDetailTvShow() {
        val dummyTvShow = MutableLiveData<TvShow>()
        dummyTvShow.value = tvShowDummy
        `when`(local.getTvShowById(tvShowId!!)).thenReturn(dummyTvShow)

        val tvShowEntity = LiveDataTestUtil.getValue(repository.getTvShowById(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(tvShowDummy.name, tvShowEntity.name)
    }

}