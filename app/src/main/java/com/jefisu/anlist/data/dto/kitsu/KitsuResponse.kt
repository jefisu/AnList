package com.jefisu.anlist.data.dto.kitsu

import kotlinx.serialization.Serializable

@Serializable
data class KitsuResponse(
    val data: List<Media>,
    val links: LinksX,
    val meta: MetaX
)