package com.masscode.moviejetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.bumptech.glide.load.engine.Resource
import com.masscode.moviejetpack.data.source.local.LocalDataSource
import com.masscode.moviejetpack.data.source.local.entity.Movie
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
        repository.getMovieLocal()

        val movieEntities = PagedListUtil.mockPagedList(DummyData.generateTvShowList())
        verify(local).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onTvShowReceived(tvShowResponses)
            null
        }.`when`(remote).loadTvShows(Mockito.any())

        val tvShowEntities = LiveDataTestUtil.getValue(repository.getTvShow())
        Mockito.verify(remote).loadTvShows(Mockito.any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                .onDetailReceived(movieDummy)
            null
        }.`when`(remote).getMovieById(eq(movieId!!), Mockito.any())

        val movieEntity = LiveDataTestUtil.getValue(repository.getMovieById(movieId))
        Mockito.verify(remote).getMovieById(eq(movieId), Mockito.any())
        assertNotNull(movieEntity)
        assertEquals(movieDummy, movieEntity)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback)
                .onDetailReceived(tvShowDummy)
            null
        }.`when`(remote).getTvShowById(eq(tvShowId!!), Mockito.any())

        val tvShowEntity = LiveDataTestUtil.getValue(repository.getTvShowById(tvShowId))
        Mockito.verify(remote).getTvShowById(eq(tvShowId), Mockito.any())
        assertNotNull(tvShowEntity)
        assertEquals(tvShowDummy, tvShowEntity)
    }

}