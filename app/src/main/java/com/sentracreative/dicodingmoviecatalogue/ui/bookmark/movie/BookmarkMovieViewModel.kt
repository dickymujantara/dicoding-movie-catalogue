package com.sentracreative.dicodingmoviecatalogue.ui.bookmark.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity

class BookmarkMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovies() : LiveData<List<MovieEntity>> = movieCatalogueRepository.getBookmarkedMovie()
}