package com.sentracreative.dicodingmoviecatalogue.ui.bookmark.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity

class BookmarkTvShowViewModel(private val mMovieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getTvShows() : LiveData<List<TvShowEntity>> = mMovieCatalogueRepository.getBookmarkedTvShows()
}