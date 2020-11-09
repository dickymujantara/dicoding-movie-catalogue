package com.sentracreative.dicodingmoviecatalogue.data.source.local.entity

data class TvShowEntity(
    var tvShowId : String,
    var title : String,
    var genre : String,
    var description : String,
    var episode : String,
    var year : String,
    var rating : Double,
    var url_image : String
)