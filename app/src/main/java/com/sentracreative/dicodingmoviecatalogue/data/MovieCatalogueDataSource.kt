package com.sentracreative.dicodingmoviecatalogue.data

import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity

interface MovieCatalogueDataSource {

    fun getAllMovies() : List<MovieEntity>
    fun getMovie(movieId : String) : MovieEntity

    fun getAllTvShows() : List<TvShowEntity>
    fun getTvShow(tvShowId : String) : TvShowEntity

}