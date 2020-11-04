package com.sentracreative.dicodingmoviecatalogue.detail.movie

import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy

class DetailMovieViewModel : ViewModel() {
    private lateinit var movieId : String

    fun setSelectedMovie(movieId : String){
        this.movieId = movieId
    }

    fun getMovies() : List<MovieEntity> = DataDummy.generateMovies()

    fun getSelectedMovie() : MovieEntity{
        lateinit var movie : MovieEntity
        val listMovies = getMovies()

        for (getMovie in listMovies){
            if (getMovie.movieId == movieId){
                movie = getMovie
                break
            }
        }

        return movie
    }

}