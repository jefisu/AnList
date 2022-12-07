package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val pagination: Pagination,
    val data: List<AnimeData>,
    val links: Links? = null,
    val meta: Meta? = null
)