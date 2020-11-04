package com.sentracreative.dicodingmoviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies() : List<MovieEntity> = DataDummy.generateMovies()
}