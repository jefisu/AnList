package com.jefisu.anlist.data.dto.jikan_moe.recommendations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageSize(
    @SerialName("image_url") val imageUrl: String?,
    @SerialName("small_image_url") val smallImageUrl: String?,
    @SerialName("large_image_url") val largeImageUrl: String?
)