package com.jefisu.anlist.data.dto.jikan_moe.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    @SerialName("data") val data: List<AnimeReview>,
    val pagination: Pagination
)