package com.jefisu.anlist.data.dto.jikan_moe.review

import kotlinx.serialization.Serializable

@Serializable
data class Images(
    val jpg: ImageContent,
    val webp: ImageContent
)