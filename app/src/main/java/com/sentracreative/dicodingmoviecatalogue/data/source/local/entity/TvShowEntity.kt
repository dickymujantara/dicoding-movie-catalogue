package com.sentracreative.dicodingmoviecatalogue.data.source.local.entity

data class TvShowEntity(
    val tvShowId : String,
    val title : String,
    val genre : String,
    val description : String,
    val episode : String,
    val year : String,
    val rating : Double,
    val url_image : String
)