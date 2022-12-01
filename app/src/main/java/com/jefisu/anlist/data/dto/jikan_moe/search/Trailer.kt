package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    @SerialName("youtube_id") val youtubeId: String?,
    val url: String?,
    @SerialName("embed_url") val embedUrl: String?,
    val images: ImageSize
)