package com.sentracreative.dicodingmoviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.sentracreative.dicodingmoviecatalogue.data.TvShowEntity
import com.sentracreative.dicodingmoviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShows() : List<TvShowEntity> = DataDummy.generateTvShows()

}