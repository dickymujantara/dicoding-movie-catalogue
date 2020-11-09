package com.sentracreative.dicodingmoviecatalogue.data

import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.RemoteDataSource

class MovieCatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieCatalogueDataSource {

    companion object{
        @Volatile
        private var instance : MovieCatalogueRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource) : MovieCatalogueRepository =
            instance ?: synchronized(this){
                instance ?: MovieCatalogueRepository(remoteDataSource)
            }

    }

    override fun getAllMovies(): List<MovieEntity> {
        val movieResponse = remoteDataSource.getAllMovies()
        val movieList = ArrayList<MovieEntity>()

        for (response in movieResponse){
            val movie = MovieEntity(
                response.movieId,
                response.title,
                response.description,
                response.genre,
                response.year,
                response.duration,
                response.rated,
                response.score,
                response.url_image
            )

            movieList.add(movie)

        }

        return movieList
    }

    override fun getMovie(movieId: String): MovieEntity {
        val response = remoteDataSource.getMovies(movieId)

        return MovieEntity(
            response.movieId,
            response.title,
            response.description,
            response.genre,
            response.year,
            response.duration,
            response.rated,
            response.score,
            response.url_image
        )
    }

    override fun getAllTvShows(): List<TvShowEntity> {
        val tvShowResponse = remoteDataSource.getAllTvShows()
        val listTvShow = ArrayList<TvShowEntity>()

        for (response in tvShowResponse){
            val tvShow = TvShowEntity(
                response.tvShowId,
                response.title,
                response.genre,
                response.description,
                response.episode,
                response.year,
                response.rating,
                response.url_image
            )

            listTvShow.add(tvShow)
        }

        return listTvShow
    }

    override fun getTvShow(tvShowId: String): TvShowEntity {
        val response = remoteDataSource.getTvShow(tvShowId)

        return TvShowEntity(
            response.tvShowId,
            response.title,
            response.genre,
            response.description,
            response.episode,
            response.year,
            response.rating,
            response.url_image
        )
    }

}