package com.sentracreative.dicodingmoviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.vo.Resource

interface MovieCatalogueDataSource {

    fun getAllMovies() : LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovie(movieId : String) : LiveData<Resource<MovieEntity>>
    fun setMovieBookmark(movie : MovieEntity, state : Boolean)
    fun getBookmarkedMovie() : LiveData<PagedList<MovieEntity>>

    fun getAllTvShows() : LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShow(tvShowId : String) : LiveData<Resource<TvShowEntity>>
    fun setTvShowBookmark(tvShowEntity: TvShowEntity, state : Boolean)
    fun getBookmarkedTvShows() : LiveData<PagedList<TvShowEntity>>


}