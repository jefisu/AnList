package com.jefisu.anlist.presentation.home

import com.jefisu.anlist.domain.model.Anime

data class HomeState(
    val topAiringAnime: List<Anime> = emptyList(),
    val animesByGenre: Map<String, List<Anime>> = emptyMap(),
    val progress: Float = 0f,
    val isLoading: Boolean = false
)