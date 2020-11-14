package com.sentracreative.dicodingmoviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.di.Injection
import com.sentracreative.dicodingmoviecatalogue.ui.bookmark.movie.BookmarkMovieViewModel
import com.sentracreative.dicodingmoviecatalogue.ui.bookmark.tvshow.BookmarkTvShowViewModel
import com.sentracreative.dicodingmoviecatalogue.ui.detail.movie.DetailMovieViewModel
import com.sentracreative.dicodingmoviecatalogue.ui.detail.tvshow.DetailTvShowViewModel
import com.sentracreative.dicodingmoviecatalogue.ui.movie.MovieViewModel
import com.sentracreative.dicodingmoviecatalogue.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mMovieCatalogueRepository: MovieCatalogueRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                DetailTvShowViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkMovieViewModel::class.java) -> {
                BookmarkMovieViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkTvShowViewModel::class.java) -> {
                BookmarkTvShowViewModel(mMovieCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}