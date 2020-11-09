package com.sentracreative.dicodingmoviecatalogue.di

import android.content.Context
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.RemoteDataSource
import com.sentracreative.dicodingmoviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : MovieCatalogueRepository{
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        
        return MovieCatalogueRepository.getInstance(remoteDataSource)
    }
}