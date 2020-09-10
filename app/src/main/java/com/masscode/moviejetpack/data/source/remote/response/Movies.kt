package com.masscode.moviejetpack.data.source.remote.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    val id: Int,
    val title: String? = "",
    @Json(name = "release_date") val releaseDate: String? = "",
    val overview: String? = "",
    @Json(name = "poster_path") val posterPath: String
) : Parcelable