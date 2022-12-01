package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.Serializable

@Serializable
data class LinksX(
    val url: String?,
    val label: String,
    val active: Boolean
)