package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.Serializable

@Serializable
data class FullDate(
    val day: Int?,
    val month: Int?,
    val year: Int?
)