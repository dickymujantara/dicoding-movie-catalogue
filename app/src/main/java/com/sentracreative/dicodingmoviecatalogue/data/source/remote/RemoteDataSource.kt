package com.sentracreative.dicodingmoviecatalogue.data.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.MovieResponse
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.TvShowResponse
import com.sentracreative.dicodingmoviecatalogue.utils.EspressoldingResource
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

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoldingResource.increment()

        val resultMovies = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        handler.postDelayed({
            resultMovies.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoldingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultMovies
    }

    fun getMovies(movieId : String) : LiveData<ApiResponse<MovieResponse>>{
        EspressoldingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieResponse>>()

        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadMovie(movieId))
            EspressoldingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultMovie
    }

    fun getAllTvShows() : LiveData<ApiResponse<List<TvShowResponse>>>{
        EspressoldingResource.increment()

        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()

        handler.postDelayed({
            resultTvShow.value = ApiResponse.success(jsonHelper.loadTvShows())
            EspressoldingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultTvShow
    }

    fun getTvShow(tvShowId : String) : LiveData<ApiResponse<TvShowResponse>>{
        EspressoldingResource.increment()

        val resultTvShow = MutableLiveData<ApiResponse<TvShowResponse>>()

        handler.postDelayed({
            resultTvShow.value = ApiResponse.success(jsonHelper.loadTvShow(tvShowId))
            EspressoldingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultTvShow
    }
}