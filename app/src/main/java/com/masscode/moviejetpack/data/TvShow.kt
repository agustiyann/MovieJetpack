package com.masscode.moviejetpack.data

data class TvShow(
    val id: Int,
    val name: String,
    val firstAirDate: String,
    val overview: String,
    val posterPath: Int,
    val trailer: String
)