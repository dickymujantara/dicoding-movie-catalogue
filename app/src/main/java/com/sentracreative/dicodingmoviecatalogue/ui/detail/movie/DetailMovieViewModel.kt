package com.sentracreative.dicodingmoviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    private lateinit var movieId : String

    fun setSelectedMovie(movieId : String){
        this.movieId = movieId
    }

    fun getSelectedMovie() : LiveData<MovieEntity> = movieCatalogueRepository.getMovie(movieId)

}