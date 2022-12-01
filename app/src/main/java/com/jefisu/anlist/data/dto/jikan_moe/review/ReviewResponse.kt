package com.jefisu.anlist.data.dto.jikan_moe.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias AnimeReviews = ReviewResponse<AnimeReview>
typealias MangaReviews = ReviewResponse<MangaReview>

@Serializable
data class ReviewResponse<T>(
    @SerialName("data") val data: List<T>,
    val pagination: Pagination
)