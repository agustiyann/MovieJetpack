package com.masscode.moviejetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.masscode.moviejetpack.data.source.local.LocalDataSource
import com.masscode.moviejetpack.data.source.remote.RemoteDataSource
import com.masscode.moviejetpack.utils.DummyData
import com.masscode.moviejetpack.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

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
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onMovieReceived(movieResponses)
            null
        }.`when`(remote).loadMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(repository.getMovies())
        verify(remote).loadMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onTvShowReceived(tvShowResponses)
            null
        }.`when`(remote).loadTvShows(any())

        val tvShowEntities = LiveDataTestUtil.getValue(repository.getTvShow())
        verify(remote).loadTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                .onDetailReceived(movieDummy)
            null
        }.`when`(remote).getMovieById(eq(movieId!!), any())

        val movieEntity = LiveDataTestUtil.getValue(repository.getMovieById(movieId))
        verify(remote).getMovieById(eq(movieId), any())
        assertNotNull(movieEntity)
        assertEquals(movieDummy, movieEntity)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback)
                .onDetailReceived(tvShowDummy)
            null
        }.`when`(remote).getTvShowById(eq(tvShowId!!), any())

        val tvShowEntity = LiveDataTestUtil.getValue(repository.getTvShowById(tvShowId))
        verify(remote).getTvShowById(eq(tvShowId), any())
        assertNotNull(tvShowEntity)
        assertEquals(tvShowDummy, tvShowEntity)
    }

}