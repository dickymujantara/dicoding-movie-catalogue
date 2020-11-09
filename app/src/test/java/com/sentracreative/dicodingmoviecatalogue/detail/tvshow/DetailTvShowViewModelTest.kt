package com.sentracreative.dicodingmoviecatalogue.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.ui.detail.tvshow.DetailTvShowViewModel
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
class DetailTvShowViewModelTest{
    private lateinit var viewModel : DetailTvShowViewModel
    private val dummy = DataDummy.generateTvShows()[0]
    private val tvShowId = dummy.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<TvShowEntity>

    @Before
    fun setup(){
        viewModel = DetailTvShowViewModel(movieCatalogueRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getSelectedTvShow(){
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummy

        `when`(movieCatalogueRepository.getTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getSelectedTvShow().value as TvShowEntity
        verify(movieCatalogueRepository).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)

        assertNotNull(tvShow)
        assertEquals(dummy.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummy.title, tvShowEntity.title)
        assertEquals(dummy.year, tvShowEntity.year)
        assertEquals(dummy.genre, tvShowEntity.genre)
        assertEquals(dummy.episode, tvShowEntity.episode)
        assertEquals(dummy.description, tvShowEntity.description)
        assertEquals(dummy.url_image, tvShowEntity.url_image)

        viewModel.getSelectedTvShow().observeForever(observer)
        verify(observer).onChanged(dummy)

    }

}