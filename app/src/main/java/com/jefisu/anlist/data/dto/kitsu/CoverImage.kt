package com.jefisu.anlist.data.dto.kitsu

import kotlinx.serialization.Serializable

@Serializable
data class CoverImage(
    val large: String,
    val meta: Meta,
    val original: String,
    val small: String,
    val tiny: String
)