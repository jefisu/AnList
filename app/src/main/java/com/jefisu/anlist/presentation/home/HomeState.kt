package com.jefisu.anlist.presentation.home

import com.jefisu.anlist.domain.model.Anime

data class HomeState(
    val topAiringAnime: List<Anime> = emptyList(),
    val animes: List<Anime> = emptyList(),
    val progress: Float = 0f,
    val isLoading: Boolean = false
)