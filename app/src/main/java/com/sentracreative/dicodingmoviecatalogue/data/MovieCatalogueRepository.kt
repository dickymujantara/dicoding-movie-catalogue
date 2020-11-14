package com.sentracreative.dicodingmoviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sentracreative.dicodingmoviecatalogue.data.source.local.LocalDataSource
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.ApiResponse
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.RemoteDataSource
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.MovieResponse
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.response.TvShowResponse
import com.sentracreative.dicodingmoviecatalogue.utils.AppExecutors
import com.sentracreative.dicodingmoviecatalogue.vo.Resource

class MovieCatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogueDataSource {

    companion object{
        @Volatile
        private var instance : MovieCatalogueRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors) : MovieCatalogueRepository =
            instance ?: synchronized(this){
                instance ?: MovieCatalogueRepository(remoteDataSource, localData, appExecutors)
            }

    }

    override fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> {

        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<MovieEntity>> = localDataSource.getAllMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> = remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()

                for (response in data){
                    val movie = MovieEntity(
                        response.movieId,
                        response.title,
                        response.description,
                        response.genre,
                        response.year,
                        response.duration,
                        response.rated,
                        response.score,
                        response.urlImage
                    )

                    movieList.add(movie)

                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovie(movieId: String): LiveData<Resource<MovieEntity>> {

        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors){
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> = remoteDataSource.getMovies(movieId)

            override fun saveCallResult(movieResponse: MovieResponse) {
                val response = MovieEntity(
                    movieResponse.movieId,
                    movieResponse.title,
                    movieResponse.description,
                    movieResponse.genre,
                    movieResponse.year,
                    movieResponse.duration,
                    movieResponse.rated,
                    movieResponse.score,
                    movieResponse.urlImage
                )
                localDataSource.updateMovie(response)
            }
        }.asLiveData()

    }

    override fun setMovieBookmark(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setBookmarkedMovie(movie,state) }

    override fun getBookmarkedMovie(): LiveData<List<MovieEntity>> = localDataSource.getBookmarkedMovie()

    override fun getAllTvShows(): LiveData<Resource<List<TvShowEntity>>> {

        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<TvShowEntity>> = localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> = remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val listTvShow = ArrayList<TvShowEntity>()

                for (response in data){
                    val tvShow = TvShowEntity(
                        response.tvShowId,
                        response.title,
                        response.genre,
                        response.description,
                        response.episode,
                        response.year,
                        response.rating,
                        response.urlImage
                    )

                    listTvShow.add(tvShow)
                }

                localDataSource.insertTvShow(listTvShow)

            }
        }.asLiveData()

    }

    override fun getTvShow(tvShowId: String): LiveData<Resource<TvShowEntity>> {

        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShow(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> = remoteDataSource.getTvShow(tvShowId)

            override fun saveCallResult(tvShowResponse: TvShowResponse) {
                val response = TvShowEntity(
                    tvShowResponse.tvShowId,
                    tvShowResponse.title,
                    tvShowResponse.genre,
                    tvShowResponse.description,
                    tvShowResponse.episode,
                    tvShowResponse.year,
                    tvShowResponse.rating,
                    tvShowResponse.urlImage
                )

                localDataSource.updateTvShow(response)
            }

        }.asLiveData()

    }

    override fun setTvShowBookmark(tvShowEntity: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setBookmarkedTvShow(tvShowEntity,state) }

    override fun getBookmarkedTvShows(): LiveData<List<TvShowEntity>> = localDataSource.getBookmarkedTvShow()
}