package com.masscode.moviejetpack.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String? = "",
    val releaseDate: String? = "",
    val overview: String? = "",
    val posterPath: Int,
    val trailer: String? = ""
) : Parcelable