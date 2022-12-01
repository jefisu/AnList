package com.jefisu.anlist.data.dto.jikan_moe.recommendations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendationsResponse(
    val pagination: Pagination,
    @SerialName("data") val recommendations: List<Recommendation>
)