package com.sentracreative.dicodingmoviecatalogue.utils

import android.content.Context
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.MovieResponse
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context : Context) {

    private fun parsingFileToString(fileName : String) : String?{
        return try {
            val `is`  = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        }catch (ex : IOException){
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies() : List<MovieResponse>{
        val list = ArrayList<MovieResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponse.json"))
            val listArray = responseObject.getJSONArray("movies")

            for (i in 0 until listArray.length()){
                val movie = listArray.getJSONObject(i)
                val id = movie.getString("movieId")
                val title =  movie.getString("title")
                val desc =  movie.getString("description")
                val genre = movie.getString("genre")
                val year = movie.getString("year")
                val duration = movie.getString("duration")
                val rated = movie.getString("rated")
                val score = movie.getDouble("score")
                val image = movie.getString("url_image")

                val movieResponse = MovieResponse(id,title,desc,genre,year,duration,rated,score,image)
                list.add(movieResponse)
            }

        } catch (e : JSONException){
            e.printStackTrace()
        }
        return list
    }

    fun loadMovie(movieId : String) : MovieResponse{
        val fileName = String.format("Movie_%s.json",movieId)
        var movieResponse : MovieResponse? = null

        try {
            val result = parsingFileToString(fileName)

            if (result != null){
                val responseObject = JSONObject(result)
                val movieId = responseObject.getString("movieId")
                val title = responseObject.getString("title")
                val desc = responseObject.getString("description")
                val genre = responseObject.getString("genre")
                val year = responseObject.getString("year")
                val duration = responseObject.getString("duration")
                val rated = responseObject.getString("rated")
                val score = responseObject.getDouble("score")
                val image = responseObject.getString("url_image")

                movieResponse = MovieResponse(movieId,title,desc,genre,year,duration,rated,score,image)
            }

        }catch (e : JSONException){
            e.printStackTrace()
        }

        return movieResponse!!
    }

    fun loadTvShows() : List<TvShowResponse>{
        val list = ArrayList<TvShowResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponse.json"))
            val listArray = responseObject.getJSONArray("tvshow")

            for (i in 0 until listArray.length()){
                val tvshow = listArray.getJSONObject(i)
                val id = tvshow.getString("tvShowId")
                val title = tvshow.getString("title")
                val genre = tvshow.getString("genre")
                val desc = tvshow.getString("description")
                val episode = tvshow.getString("episode")
                val year = tvshow.getString("year")
                val rating = tvshow.getDouble("rating")
                val image = tvshow.getString("url_image")

                val tvShowResponse = TvShowResponse(id,title,genre,desc,episode,year,rating,image)
                list.add(tvShowResponse)

            }

        }catch (e : JSONException){
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShow(tvShowId : String) : TvShowResponse{
        val fileName = String.format("TvShow_%s.json",tvShowId)
        var tvShowResponse : TvShowResponse? = null

        try {
            val result = parsingFileToString(fileName)

            if (result != null){
                val responseObject = JSONObject(result)
                val id = responseObject.getString("tvShowId")
                val title = responseObject.getString("title")
                val genre = responseObject.getString("genre")
                val desc = responseObject.getString("description")
                val eps = responseObject.getString("episode")
                val year = responseObject.getString("year")
                val rating = responseObject.getDouble("rating")
                val image = responseObject.getString("url_image")

                tvShowResponse = TvShowResponse(id,title,genre,desc,eps,year,rating,image)
            }

        }catch (e : JSONException){
            e.printStackTrace()
        }

        return tvShowResponse!!
    }

}