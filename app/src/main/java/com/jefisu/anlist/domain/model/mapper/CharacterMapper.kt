package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.character.CharacterDto
import com.jefisu.anlist.domain.model.Character

fun CharacterDto.toCharacter() = Character(
    name = name,
    image = images.jpg.imageUrl
)