package com.jefisu.anlist.presentation.detail

import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.Character
import com.jefisu.anlist.domain.model.Review

data class DetailState(
    val anime: Anime? = null,
    val reviews: List<Review> = emptyList(),
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false
)