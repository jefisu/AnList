package com.jefisu.anlist.presentation.home

import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.Recommendation

data class HomeState(
    val topAiringAnime: List<Anime> = emptyList(),
    val recommendationsAnime: List<Recommendation> = emptyList(),
    val isLoading: Boolean = false
)