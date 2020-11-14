package com.sentracreative.dicodingmoviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowResponse(
    val tvShowId : String,
    val title : String,
    val genre : String,
    val description : String,
    val episode : String,
    val year : String,
    val rating : Double,
    val urlImage : String
) : Parcelable