package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("current_page") val currentPage: Int,
    val from: Int,
    @SerialName("last_page") val lastPage: Int,
    val links: List<LinksX>,
    val path: String,
    @SerialName("per_page")val perPage: Int,
    val to: Int,
    val total: Int
)