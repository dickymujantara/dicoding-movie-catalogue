package com.sentracreative.dicodingmoviecatalogue.data.source.remote

import android.os.Handler
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.MovieResponse
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.TvShowResponse
import com.sentracreative.dicodingmoviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){
    private val handler = Handler()

    companion object{
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance : RemoteDataSource? = null

        fun getInstance(helper : JsonHelper) : RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovies(callback : LoadMoviesCallback){
        handler.postDelayed({callback.onAllMoviesReceived(jsonHelper.loadMovies())}, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovies(movieId : String, callback : loadMovieCallback){
        handler.postDelayed({callback.onMovieReceived(jsonHelper.loadMovie(movieId))}, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShows(callback : loadTvShowsCallback){
        handler.postDelayed({callback.onAllTvShowsReceived(jsonHelper.loadTvShows())}, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getTvShow(tvShowId : String, callback : loadTvShowCallback){
        handler.postDelayed({callback.onTvShowReceived(jsonHelper.loadTvShow(tvShowId))}, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback{
        fun onAllMoviesReceived(movieResponses : List<MovieResponse>)
    }

    interface loadMovieCallback{
        fun onMovieReceived(movieResponse: MovieResponse)
    }

    interface loadTvShowsCallback{
        fun onAllTvShowsReceived(tvShowResponses : List<TvShowResponse>)
    }

    interface loadTvShowCallback{
        fun onTvShowReceived(tvShowResponse: TvShowResponse)
    }

}