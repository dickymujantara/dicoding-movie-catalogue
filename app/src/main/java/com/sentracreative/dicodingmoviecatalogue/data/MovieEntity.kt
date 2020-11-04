package com.sentracreative.dicodingmoviecatalogue.data

data class MovieEntity(
    var movieId : String,
    var title : String,
    var description : String,
    var genre : String,
    var year : String,
    var duration: String,
    var rated : String,
    var score : Double,
    var url_image : String
)