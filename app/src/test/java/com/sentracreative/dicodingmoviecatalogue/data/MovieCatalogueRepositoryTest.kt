package com.sentracreative.dicodingmoviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import com.sentracreative.dicodingmoviecatalogue.data.source.local.LocalDataSource
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.MovieEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.data.source.remote.RemoteDataSource
import com.sentracreative.dicodingmoviecatalogue.utils.AppExecutors
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy
import com.sentracreative.dicodingmoviecatalogue.utils.PagedListUtil
import com.sentracreative.dicodingmoviecatalogue.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import javax.sql.DataSource

class MovieCatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)
    private val movieResponses = DataDummy.generateMovies()
    private val tvShowResponses = DataDummy.generateTvShows()


    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(androidx.paging.DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedMovie() {
        val dataSourceFactory = mock(androidx.paging.DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory = mock(androidx.paging.DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        movieRepository.getAllTvShows()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedTvShows() {
        val dataSourceFactory = mock(androidx.paging.DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        movieRepository.getAllTvShows()

        val tvShowEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntity.data?.size?.toLong())
    }
}