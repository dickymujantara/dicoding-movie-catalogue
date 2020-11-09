package com.sentracreative.dicodingmoviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowResponse(
    var tvShowId : String,
    var title : String,
    var genre : String,
    var description : String,
    var episode : String,
    var year : String,
    var rating : Double,
    var url_image : String
) : Parcelable