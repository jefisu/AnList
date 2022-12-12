package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.search.SearchResponse
import com.jefisu.anlist.domain.model.DataResponse

fun SearchResponse.toDataResponse() = DataResponse(
    items = data.map { it.toAnime() },
    totalItems = pagination.items.total
)