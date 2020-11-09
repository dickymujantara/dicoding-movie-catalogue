package com.sentracreative.dicodingmoviecatalogue.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.ui.detail.movie.DetailMovieViewModel
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest{
    private lateinit var viewModel: DetailMovieViewModel
    private val dummy = DataDummy.generateMovies()[0]
    private val movieId = dummy.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setup(){
        viewModel = DetailMovieViewModel(movieCatalogueRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getSelectedMovie(){
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummy

        `when`(movieCatalogueRepository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getSelectedMovie().value as MovieEntity
        verify(movieCatalogueRepository).getMovie(movieId)
        assertNotNull(movieEntity)

        assertEquals(dummy.movieId, movieEntity.movieId)
        assertEquals(dummy.title, movieEntity.title)
        assertEquals(dummy.description, movieEntity.description)
        assertEquals(dummy.duration, movieEntity.duration)
        assertEquals(dummy.genre, movieEntity.genre)
        assertEquals(dummy.rated, movieEntity.rated)
        assertEquals(dummy.score.toString(), movieEntity.score.toString())
        assertEquals(dummy.year, movieEntity.year)
        assertEquals(dummy.url_image, movieEntity.url_image)


        viewModel.getSelectedMovie().observeForever(observer)
        verify(observer).onChanged(dummy)

    }

}