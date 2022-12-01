package com.jefisu.anlist.data.dto.jikan_moe.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Jpg(
    @SerialName("image_url")val imageUrl: String
)