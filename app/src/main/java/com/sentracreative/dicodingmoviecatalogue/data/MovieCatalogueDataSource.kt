package com.sentracreative.dicodingmoviecatalogue.data

import androidx.lifecycle.LiveData
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.vo.Resource

interface MovieCatalogueDataSource {

    fun getAllMovies() : LiveData<Resource<List<MovieEntity>>>
    fun getMovie(movieId : String) : LiveData<Resource<MovieEntity>>
    fun setMovieBookmark(movie : MovieEntity, state : Boolean)
    fun getBookmarkedMovie() : LiveData<List<MovieEntity>>

    fun getAllTvShows() : LiveData<Resource<List<TvShowEntity>>>
    fun getTvShow(tvShowId : String) : LiveData<Resource<TvShowEntity>>
    fun setTvShowBookmark(tvShowEntity: TvShowEntity, state : Boolean)
    fun getBookmarkedTvShows() : LiveData<List<TvShowEntity>>


}