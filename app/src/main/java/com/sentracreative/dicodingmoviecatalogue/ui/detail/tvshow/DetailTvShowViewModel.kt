package com.sentracreative.dicodingmoviecatalogue.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy

class DetailTvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    private lateinit var tvShowId : String

    fun setSelectedTvShow(tvShowId : String){
        this.tvShowId = tvShowId
    }

    fun getSelectedTvShow() : TvShowEntity = movieCatalogueRepository.getTvShow(tvShowId)

}