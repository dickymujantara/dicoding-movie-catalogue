package com.sentracreative.dicodingmoviecatalogue.data

import androidx.lifecycle.LiveData
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity

interface MovieCatalogueDataSource {

    fun getAllMovies() : LiveData<List<MovieEntity>>
    fun getMovie(movieId : String) : LiveData<MovieEntity>

    fun getAllTvShows() : LiveData<List<TvShowEntity>>
    fun getTvShow(tvShowId : String) : LiveData<TvShowEntity>

}