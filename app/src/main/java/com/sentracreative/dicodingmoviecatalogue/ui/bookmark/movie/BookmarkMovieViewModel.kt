package com.sentracreative.dicodingmoviecatalogue.ui.bookmark.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity

class BookmarkMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovies() : LiveData<PagedList<MovieEntity>> = movieCatalogueRepository.getBookmarkedMovie()

    fun setBookmark(movieEntity: MovieEntity){
        val newState = !movieEntity.bookmarked
        movieCatalogueRepository.setMovieBookmark(movieEntity,newState)
    }

}