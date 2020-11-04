package com.sentracreative.dicodingmoviecatalogue.detail.tvshow

import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy

class DetailTvShowViewModel : ViewModel() {
    private lateinit var tvShowId : String

    fun setSelectedTvShow(tvShowId : String){
        this.tvShowId = tvShowId
    }

    fun getTvShows() : List<TvShowEntity> = DataDummy.generateTvShows()

    fun getSelectedTvShow() : TvShowEntity{
        lateinit var tvShow : TvShowEntity
        val listTvShow = getTvShows()

        for (getTvShow in listTvShow){
            if (getTvShow.tvShowId == tvShowId){
                tvShow = getTvShow
                break
            }
        }

        return tvShow
    }

}