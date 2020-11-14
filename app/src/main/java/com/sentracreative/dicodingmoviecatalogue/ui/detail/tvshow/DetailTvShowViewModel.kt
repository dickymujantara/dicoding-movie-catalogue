package com.sentracreative.dicodingmoviecatalogue.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.MovieCatalogueRepository
import com.sentracreative.dicodingmoviecatalogue.data.source.local.entity.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy
import com.sentracreative.dicodingmoviecatalogue.vo.Resource

class DetailTvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    val tvShowId = MutableLiveData<String>()

    fun setSelectedTvShow(tvShowId : String){
        this.tvShowId.value = tvShowId
    }

    var selectedTvShow : LiveData<Resource<TvShowEntity>> = Transformations.switchMap(tvShowId){
        movieCatalogueRepository.getTvShow(it)
    }

    fun setBookmark(){
        val tvShowResource = selectedTvShow.value?.data

        if (tvShowResource != null){
            val newState = !tvShowResource.bookmarked
            movieCatalogueRepository.setTvShowBookmark(tvShowResource,newState)
        }

    }

}