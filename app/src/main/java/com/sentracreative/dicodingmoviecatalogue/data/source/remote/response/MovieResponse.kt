package com.sentracreative.dicodingmoviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    var movieId : String,
    var title : String,
    var description : String,
    var genre : String,
    var year : String,
    var duration: String,
    var rated : String,
    var score : Double,
    var url_image : String
) : Parcelable