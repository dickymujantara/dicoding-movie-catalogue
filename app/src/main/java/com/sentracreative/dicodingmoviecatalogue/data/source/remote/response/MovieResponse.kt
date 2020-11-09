package com.sentracreative.dicodingmoviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    val movieId: String,
    val title: String,
    val description: String,
    val genre: String,
    val year: String,
    val duration: String,
    val rated: String,
    val score: Double,
    val url_image: String
) : Parcelable