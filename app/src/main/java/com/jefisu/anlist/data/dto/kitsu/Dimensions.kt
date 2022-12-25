package com.jefisu.anlist.data.dto.kitsu

import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
    val large: Size,
    val small: Size,
    val tiny: Size
)