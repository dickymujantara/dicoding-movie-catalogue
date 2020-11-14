package com.sentracreative.dicodingmoviecatalogue.di

import android.content.Context
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.LocalDataSource
import com.sentracreative.dicodingmoviecatalogue.data.source.local.room.MovieDatabase
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.RemoteDataSource
import com.sentracreative.dicodingmoviecatalogue.utils.AppExecutors
import com.sentracreative.dicodingmoviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : MovieCatalogueRepository{
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        
        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}