package com.sentracreative.dicodingmoviecatalogue.data.source.remote

import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.MovieResponse
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.TvShowResponse
import com.sentracreative.dicodingmoviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){
    companion object{
        @Volatile
        private var instance : RemoteDataSource? = null

        fun getInstance(helper : JsonHelper) : RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovies() : List<MovieResponse> = jsonHelper.loadMovies()
    fun getMovies(movieId : String) : MovieResponse = jsonHelper.loadMovie(movieId)

    fun getAllTvShows() : List<TvShowResponse> = jsonHelper.loadTvShows()
    fun getTvShow(tvShowId : String) : TvShowResponse = jsonHelper.loadTvShow(tvShowId)

}