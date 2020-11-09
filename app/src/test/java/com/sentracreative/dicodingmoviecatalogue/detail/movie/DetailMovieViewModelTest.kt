package com.sentracreative.dicodingmoviecatalogue.detail.movie

import com.sentracreative.dicodingmoviecatalogue.ui.detail.movie.DetailMovieViewModel
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest{
    private lateinit var viewModel: DetailMovieViewModel
    private val dummy = DataDummy.generateMovies()[0]
    private val movieId = dummy.movieId

    @Before
    fun setup(){
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getListMovie(){
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }

    @Test
    fun getSelectedMovie(){
        val movie = viewModel.getSelectedMovie()

        assertNotNull(movie)
        assertEquals(dummy.movieId, movie.movieId)
        assertEquals(dummy.title, movie.title)
        assertEquals(dummy.year, movie.year)
        assertEquals(dummy.rated, movie.rated)
        assertEquals(dummy.genre, movie.genre)
        assertEquals(dummy.duration, movie.duration)
        assertEquals(dummy.description, movie.description)
        assertEquals(dummy.url_image, movie.url_image)

    }

}