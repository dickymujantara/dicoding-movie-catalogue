package com.sentracreative.dicodingmoviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies() :  DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM movieentities WHERE bookmarked = 1")
    fun getBookmarkedMovie() : DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieById(movieId : String) : LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie : List<MovieEntity>)

    @Update
    fun updateMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM tvshowentities")
    fun getTvShows() : DataSource.Factory<Int,TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE bookmarked = 1")
    fun getBookmarkedTvShow() : DataSource.Factory<Int,TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId : String) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow : List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShowEntity: TvShowEntity)

}