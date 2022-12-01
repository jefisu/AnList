package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageSize(
    @SerialName("image_url") val imageUrl: String?,
    @SerialName("large_image_url") val largeImageUrl: String?,
    @SerialName("small_image_url") val smallImageUrl: String?,
    @SerialName("maximum_image_url") val maximumImageUrl: String? = null,
    @SerialName("medium_image_url") val mediumImageUrl: String? = null
)