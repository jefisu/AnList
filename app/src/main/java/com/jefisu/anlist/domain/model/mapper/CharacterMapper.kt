package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.character.AnimeCharacter
import com.jefisu.anlist.data.dto.jikan_moe.character.MangaCharacter
import com.jefisu.anlist.domain.model.Character

fun AnimeCharacter.toCharacter() = Character(
    name = character.name,
    image = character.images.jpg.imageUrl,
    voiceActor = voiceActors
        .firstOrNull { it.language == "Japanese" }
        ?.person?.name.orEmpty()
)

fun MangaCharacter.toCharacter() = Character(
    name = character.name,
    image = character.images.jpg.imageUrl,
    voiceActor = null
)