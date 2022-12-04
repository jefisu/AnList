package com.jefisu.anlist.data.dto.jikan_moe.recommendations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendationDto(
    @SerialName("mal_id") val malId: String,
    val entry: List<Entry>,
    val content: String,
    val date: String,
    val user: User
)