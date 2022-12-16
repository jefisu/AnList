package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.character.AnimeCharacter
import com.jefisu.anlist.domain.model.Character

fun AnimeCharacter.toCharacter(): Character {
    val voiceActor = voiceActors
        .firstOrNull { it.language == "Japanese" }
        ?.person

    return Character(
        name = character.name,
        image = character.images.jpg.imageUrl,
        voiceActorName = voiceActor?.name,
        voiceActorImage = voiceActor?.images?.jpg?.imageUrl
    )
}