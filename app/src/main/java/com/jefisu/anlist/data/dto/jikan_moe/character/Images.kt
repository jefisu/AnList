package com.jefisu.anlist.data.dto.jikan_moe.character

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val jpg: Jpg,
    val webp: Webp
)