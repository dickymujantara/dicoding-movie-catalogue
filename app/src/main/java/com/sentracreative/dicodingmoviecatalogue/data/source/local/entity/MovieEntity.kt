package com.sentracreative.dicodingmoviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId : String,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name = "description")
    val description : String,

    @ColumnInfo(name = "genre")
    val genre : String,

    @ColumnInfo(name = "year")
    val year : String,

    @ColumnInfo(name = "duration")
    val duration: String,

    @ColumnInfo(name = "rated")
    val rated : String,

    @ColumnInfo(name = "score")
    val score : Double,

    @ColumnInfo(name = "urlImage")
    val urlImage : String,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false

)