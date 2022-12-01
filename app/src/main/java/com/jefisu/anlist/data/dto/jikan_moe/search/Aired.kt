package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.Serializable

@Serializable
data class Aired(
    val from: String?,
    val to: String?,
    val prop: Prop,
    val string: String?
)