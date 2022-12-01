package com.jefisu.anlist.data.dto.jikan_moe.recommendations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("has_next_page") val hasNextPage: Boolean,
    @SerialName("last_visible_page") val lastVisiblePage: Int
)