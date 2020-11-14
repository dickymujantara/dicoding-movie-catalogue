package com.sentracreative.dicodingmoviecatalogue.ui.detail.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy
import com.sentracreative.dicodingmoviecatalogue.vo.Resource

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    val movieId = MutableLiveData<String>()

    fun setSelectedMovie(movieId : String){
        this.movieId.value = movieId
    }

    var selectedMovie : LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId){
        movieCatalogueRepository.getMovie(it)
    }

    fun setBookmark(){
        val movieResource = selectedMovie.value?.data

        if (movieResource != null){
            val newState = !movieResource.bookmarked
            movieCatalogueRepository.setMovieBookmark(movieResource,newState)
        }

    }

}