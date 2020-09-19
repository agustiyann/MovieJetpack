package com.masscode.moviejetpack.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_entity")
data class Movie(
    @PrimaryKey
    val id: Int? = 0,
    val title: String? = "",
    @Json(name = "release_date") val releaseDate: String? = "",
    val overview: String? = "",
    @Json(name = "poster_path") val posterPath: String? = "",
    @Json(name = "backdrop_path") val backdropPath: String? = "",
    val popularity: Double? = 0.0,
    @Json(name = "vote_average") val voteAverage: Float? = 0f,
    var isFavorite: Boolean = false
) : Parcelable