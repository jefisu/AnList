package com.jefisu.anlist.data.dto.jikan_moe

import com.jefisu.anlist.data.dto.jikan_moe.search.AnimeData
import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponse(
    val data: AnimeData
)
