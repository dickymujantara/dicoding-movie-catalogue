package com.sentracreative.dicodingmoviecatalogue.detail.tvshow

import com.sentracreative.dicodingmoviecatalogue.ui.detail.tvshow.DetailTvShowViewModel
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailTvShowViewModelTest{
    private lateinit var viewModel : DetailTvShowViewModel
    private val dummy = DataDummy.generateTvShows()[0]
    private val tvShowId = dummy.tvShowId

    @Before
    fun setup(){
        viewModel = DetailTvShowViewModel()
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getListTvShow(){
        val movies = viewModel.getTvShows()
        assertNotNull(movies)
        assertEquals(10,movies.size)
    }

    @Test
    fun getSelectedTvShow(){
        val tvShow = viewModel.getSelectedTvShow()

        assertNotNull(tvShow)
        assertEquals(dummy.tvShowId, tvShow.tvShowId)
        assertEquals(dummy.title, tvShow.title)
        assertEquals(dummy.year, tvShow.year)
        assertEquals(dummy.genre, tvShow.genre)
        assertEquals(dummy.episode, tvShow.episode)
        assertEquals(dummy.description, tvShow.description)
        assertEquals(dummy.url_image, tvShow.url_image)
    }

}