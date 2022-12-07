package com.jefisu.anlist.data.dto.kitsu

import kotlinx.serialization.Serializable

@Serializable
data class LinksX(
    val first: String,
    val last: String,
    val next: String? = null
)