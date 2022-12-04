package com.jefisu.anlist.data.dto.jikan_moe.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("mal_id") val malId: Int,
    val url: String,
    val images: Images,
    val name: String
)