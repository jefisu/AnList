package com.jefisu.anlist.data.dto.jikan_moe.character

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val data: List<AnimeCharacter>
)