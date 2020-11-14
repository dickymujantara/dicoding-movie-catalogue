package com.sentracreative.dicodingmoviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao : MovieDao) {

    companion object{
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(movieDao: MovieDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)

    }

    fun getAllMovies() : LiveData<List<MovieEntity>> = mMovieDao.getMovies()

    fun getBookmarkedMovie() : LiveData<List<MovieEntity>> = mMovieDao.getBookmarkedMovie()

    fun getMovie(movieId : String) : LiveData<MovieEntity> = mMovieDao.getMovieById(movieId)

    fun insertMovies(movies : List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun updateMovie(movie: MovieEntity) = mMovieDao.updateMovie(movie)

    fun setBookmarkedMovie(movie: MovieEntity, newState : Boolean){
        movie.bookmarked = newState
        mMovieDao.updateMovie(movie)
    }

    fun getAllTvShows() : LiveData<List<TvShowEntity>> = mMovieDao.getTvShows()

    fun getBookmarkedTvShow() : LiveData<List<TvShowEntity>> = mMovieDao.getBookmarkedTvShow()

    fun getTvShow(tvShowId : String) : LiveData<TvShowEntity> = mMovieDao.getTvShowById(tvShowId)

    fun insertTvShow(tvShows : List<TvShowEntity>) = mMovieDao.insertTvShow(tvShows)

    fun updateTvShow(tvShow : TvShowEntity) = mMovieDao.updateTvShow(tvShow)

    fun setBookmarkedTvShow(tvShow: TvShowEntity, newState : Boolean){
        tvShow.bookmarked = newState
        mMovieDao.updateTvShow(tvShow)
    }

}