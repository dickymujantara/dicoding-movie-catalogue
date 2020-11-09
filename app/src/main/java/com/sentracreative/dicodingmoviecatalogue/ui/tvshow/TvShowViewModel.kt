package com.sentracreative.dicodingmoviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getTvShows() : LiveData<List<TvShowEntity>> = movieCatalogueRepository.getAllTvShows()

}