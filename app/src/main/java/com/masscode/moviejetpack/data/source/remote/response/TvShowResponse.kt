package com.masscode.moviejetpack.data.source.remote.response

import com.masscode.moviejetpack.data.source.local.entity.TvShow
import com.squareup.moshi.Json

data class TvShowResponse(
    val page: Int,
    @Json(name = "results") val tvShows: List<TvShow>,
    @Json(name = "total_pages") val pages: Int
)