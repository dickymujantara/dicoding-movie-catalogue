package com.sentracreative.dicodingmoviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getTvShows() : List<TvShowEntity> = movieCatalogueRepository.getAllTvShows()

}