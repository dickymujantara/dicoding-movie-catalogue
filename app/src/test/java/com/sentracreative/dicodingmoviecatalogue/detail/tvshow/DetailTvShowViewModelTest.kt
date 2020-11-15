package com.sentracreative.dicodingmoviecatalogue.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.ui.detail.tvshow.DetailTvShowViewModel
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy
import com.sentracreative.dicodingmoviecatalogue.vo.Resource
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
    private lateinit var observer: Observer<Resource<TvShowEntity>>

    @Before
    fun setup(){
        viewModel = DetailTvShowViewModel(movieCatalogueRepository)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getSelectedTvShow(){
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        val resource = Resource.success(dummy)
        tvShow.value = resource

        `when`(movieCatalogueRepository.getTvShow(tvShowId)).thenReturn(tvShow)
        viewModel.selectedTvShow.observeForever(observer)
        verify(observer).onChanged(resource)

    }

}