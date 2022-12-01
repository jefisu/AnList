package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMangaResponse(
    val pagination: Pagination,
    @SerialName("data") val manga: List<Manga>
)