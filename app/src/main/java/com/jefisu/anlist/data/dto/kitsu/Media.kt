package com.jefisu.anlist.data.dto.kitsu

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val attributes: Attributes,
    val id: String,
    val links: Links,
    val type: String
)