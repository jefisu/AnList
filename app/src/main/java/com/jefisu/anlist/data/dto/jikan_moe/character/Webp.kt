package com.jefisu.anlist.data.dto.jikan_moe.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Webp(
    @SerialName("image_url") val imageUrl: String,
    @SerialName("small_image_url")val smallImageUrl: String
)