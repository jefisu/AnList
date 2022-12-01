package com.jefisu.anlist.data.dto.jikan_moe.character

import kotlinx.serialization.Serializable

typealias AnimeCharacters = CharactersResponse<AnimeCharacter>
typealias MangaCharacters = CharactersResponse<MangaCharacter>

@Serializable
data class CharactersResponse<T>(
    val data: List<T>
)