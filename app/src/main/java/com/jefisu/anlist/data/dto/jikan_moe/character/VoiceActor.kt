package com.jefisu.anlist.data.dto.jikan_moe.character

import kotlinx.serialization.Serializable

@Serializable
data class VoiceActor(
    val person: Person,
    val language: String
)