package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataExtension(
    @SerialName("mal_id") val malId: Int,
    val type: String,
    val name: String,
    val url: String
)