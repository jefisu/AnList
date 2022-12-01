package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse<T>(
    val pagination: Pagination,
    val data: List<T>,
    val links: Links,
    val meta: Meta
)