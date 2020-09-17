//package com.masscode.moviejetpack.ui.detail
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import com.masscode.moviejetpack.data.Repository
//import com.masscode.moviejetpack.data.source.local.entity.Movie
//import com.masscode.moviejetpack.data.source.local.entity.TvShow
//import com.masscode.moviejetpack.utils.DummyData
//import com.nhaarman.mockitokotlin2.verify
//import org.junit.Test
//
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class DetailViewModelTest {
//
//    private lateinit var viewModel: DetailViewModel
//    private val movieDummy = DummyData.generateMovieList()[0]
//    private val tvShowDummy = DummyData.generateTvShowList()[0]
//
//    private val movieId = movieDummy.id
//    private val tvShowId = tvShowDummy.id
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var repository: Repository
//
//    @Mock
//    private lateinit var movieObserver: Observer<Movie>
//
//    @Mock
//    private lateinit var tvShowObserver: Observer<TvShow>
//
//    @Before
//    fun setUp() {
//        viewModel = DetailViewModel(repository)
//
//    }
//
//    @Test
//    fun getDetailMovie() {
//        val movie = MutableLiveData<Movie>()
//        movie.value = movieDummy
//
//        `when`(repository.getMovieById(movieId!!)).thenReturn(movie)
//        verify(repository).getMovieById(movieId)
//        assertNotNull(movieData)
//        assertEquals(movieDummy.id, movieData.id)
//        assertEquals(movieDummy.title, movieData.title)
//        assertEquals(movieDummy.releaseDate, movieData.releaseDate)
//        assertEquals(movieDummy.overview, movieData.overview)
//        assertEquals(movieDummy.posterPath, movieData.posterPath)
//
//        verify(movieObserver).onChanged(movieDummy)
//    }
//
//    @Test
//    fun getDetailTvShow() {
//        val tvShow = MutableLiveData<TvShow>()
//        tvShow.value = tvShowDummy
//
//        `when`(repository.getTvShowById(tvShowId!!)).thenReturn(tvShow)
//        verify(repository).getTvShowById(tvShowId)
//        assertNotNull(tvShowData)
//        assertEquals(tvShowDummy.id, tvShowData.id)
//        assertEquals(tvShowDummy.name, tvShowData.name)
//        assertEquals(tvShowDummy.firstAirDate, tvShowData.firstAirDate)
//        assertEquals(tvShowDummy.overview, tvShowData.overview)
//        assertEquals(tvShowDummy.posterPath, tvShowData.posterPath)
//        assertEquals(tvShowDummy, tvShowData)
//
//        verify(tvShowObserver).onChanged(tvShowDummy)
//    }
//}