package com.jefisu.anlist.data.dto.kitsu

import kotlinx.serialization.Serializable

@Serializable
data class Size(
    val height: Int?,
    val width: Int?
)