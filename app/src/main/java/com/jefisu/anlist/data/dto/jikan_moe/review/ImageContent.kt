package com.jefisu.anlist.data.dto.jikan_moe.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageContent(
    @SerialName("image_url")val imageUrl: String
)