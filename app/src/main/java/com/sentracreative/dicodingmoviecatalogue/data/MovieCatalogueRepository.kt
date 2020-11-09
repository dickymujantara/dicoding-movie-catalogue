package com.sentracreative.dicodingmoviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.RemoteDataSource
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.MovieResponse
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.TvShowResponse

class MovieCatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieCatalogueDataSource {

    companion object{
        @Volatile
        private var instance : MovieCatalogueRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource) : MovieCatalogueRepository =
            instance ?: synchronized(this){
                instance ?: MovieCatalogueRepository(remoteDataSource)
            }

    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in movieResponses){
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

                movieResult.postValue(movieList)
            }
        })

        return movieResult
    }

    override fun getMovie(movieId: String): LiveData<MovieEntity> {
        val result = MutableLiveData<MovieEntity>()

        remoteDataSource.getMovies(movieId, object : RemoteDataSource.loadMovieCallback{
            override fun onMovieReceived(movieResponse: MovieResponse) {
                val response = MovieEntity(
                    movieResponse.movieId,
                    movieResponse.title,
                    movieResponse.description,
                    movieResponse.genre,
                    movieResponse.year,
                    movieResponse.duration,
                    movieResponse.rated,
                    movieResponse.score,
                    movieResponse.url_image
                )

                result.postValue(response)
            }
        })

        return result
    }

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResult = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getAllTvShows(object : RemoteDataSource.loadTvShowsCallback{
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShowResponse>) {
                val listTvShow = ArrayList<TvShowEntity>()

                for (response in tvShowResponses){
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

                tvShowResult.postValue(listTvShow)
            }
        })

        return tvShowResult
    }

    override fun getTvShow(tvShowId: String): LiveData<TvShowEntity> {
        val result = MutableLiveData<TvShowEntity>()

        remoteDataSource.getTvShow(tvShowId,object : RemoteDataSource.loadTvShowCallback{
            override fun onTvShowReceived(tvShowResponse: TvShowResponse) {
                val response = TvShowEntity(
                    tvShowResponse.tvShowId,
                    tvShowResponse.title,
                    tvShowResponse.genre,
                    tvShowResponse.description,
                    tvShowResponse.episode,
                    tvShowResponse.year,
                    tvShowResponse.rating,
                    tvShowResponse.url_image
                )

                result.postValue(response)
            }
        })

        return result
    }

}