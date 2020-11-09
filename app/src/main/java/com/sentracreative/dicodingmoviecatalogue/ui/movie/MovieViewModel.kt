package com.sentracreative.dicodingmoviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovies() : List<MovieEntity> = movieCatalogueRepository.getAllMovies()
}