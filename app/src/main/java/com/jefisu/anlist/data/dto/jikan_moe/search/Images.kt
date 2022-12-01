package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val jpg: ImageSize,
    val webp: ImageSize
)