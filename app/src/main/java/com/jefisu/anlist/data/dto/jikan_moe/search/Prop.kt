package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.Serializable

@Serializable
data class Prop(
    val from: FullDate,
    val to: FullDate
)