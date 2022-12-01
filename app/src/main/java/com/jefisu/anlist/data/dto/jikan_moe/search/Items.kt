package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Items(
    val count: Int,
    @SerialName("per_page") val perPage: Int,
    val total: Int
)