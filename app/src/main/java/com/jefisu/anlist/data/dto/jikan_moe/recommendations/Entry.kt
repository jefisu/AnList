package com.jefisu.anlist.data.dto.jikan_moe.recommendations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    @SerialName("mal_id") val malId: Int,
    val url: String,
    val images: Images,
    val title: String
)