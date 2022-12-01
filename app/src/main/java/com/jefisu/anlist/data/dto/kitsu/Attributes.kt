package com.jefisu.anlist.data.dto.kitsu

import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    val canonicalTitle: String,
    val coverImage: CoverImage?
)