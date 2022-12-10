package com.jefisu.anlist.presentation.search

import com.jefisu.anlist.core.util.UiText
import com.jefisu.anlist.domain.model.Anime

data class SearchState(
    val query: String = "",
    val searchResult: List<Anime> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null
)