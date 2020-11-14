package com.sentracreative.dicodingmoviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    val tvShowId : String,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name = "genre")
    val genre : String,

    @ColumnInfo(name = "description")
    val description : String,

    @ColumnInfo(name = "episode")
    val episode : String,

    @ColumnInfo(name = "year")
    val year : String,

    @ColumnInfo(name = "rating")
    val rating : Double,

    @ColumnInfo(name = "urlImage")
    val urlImage : String,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
)