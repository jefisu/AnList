package com.jefisu.anlist.data.dto.the_movie_db

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TheMovieResponse(
    val page: Int,
    val results: List<Result>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)