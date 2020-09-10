package com.masscode.moviejetpack.data.source.local.entity

data class TvShow(
    val id: Int,
    val name: String,
    val firstAirDate: String,
    val overview: String,
    val posterPath: Int,
    val trailer: String
)