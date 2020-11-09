package com.sentracreative.dicodingmoviecatalogue.data.source.local.entity

data class MovieEntity(
    val movieId : String,
    val title : String,
    val description : String,
    val genre : String,
    val year : String,
    val duration: String,
    val rated : String,
    val score : Double,
    val url_image : String
)