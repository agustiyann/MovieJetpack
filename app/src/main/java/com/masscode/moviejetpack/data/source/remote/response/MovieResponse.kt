package com.masscode.moviejetpack.data.source.remote.response

import com.squareup.moshi.Json

data class MovieResponse(
    val page: Int,
    @Json(name = "results") val movies: List<Movies>,
    @Json(name = "total_pages") val pages: Int
)