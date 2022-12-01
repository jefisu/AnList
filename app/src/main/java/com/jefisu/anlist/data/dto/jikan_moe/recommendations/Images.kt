package com.jefisu.anlist.data.dto.jikan_moe.recommendations

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val jpg: ImageSize,
    val webp: ImageSize
)