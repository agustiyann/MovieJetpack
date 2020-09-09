package com.masscode.moviejetpack.data

data class Movie(
    val id: Int,
    val title: String? = "",
    val releaseDate: String? = "",
    val overview: String? = "",
    val posterPath: Int,
    val trailer: String? = ""
)