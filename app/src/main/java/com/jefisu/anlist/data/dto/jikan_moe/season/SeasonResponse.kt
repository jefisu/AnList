package com.jefisu.anlist.data.dto.jikan_moe.season

import com.jefisu.anlist.data.dto.jikan_moe.search.Anime
import com.jefisu.anlist.data.dto.jikan_moe.search.Pagination
import kotlinx.serialization.Serializable

@Serializable
data class SeasonResponse(
    val pagination: Pagination,
    val data: List<Anime>,
)