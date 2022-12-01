package com.jefisu.anlist.data.dto.jikan_moe.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeCharacter(
    val character: Character,
    val role: String,
    val favorites: Int,
    @SerialName("voice_actors") val voiceActors: List<VoiceActor>
)