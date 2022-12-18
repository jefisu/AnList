package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val first: String,
    val last: String,
    val prev: String?,
    val next: String?
)