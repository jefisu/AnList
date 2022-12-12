package com.jefisu.anlist.presentation.season

import com.jefisu.anlist.core.util.UiText
import com.jefisu.anlist.domain.model.Anime
import java.time.LocalDate

data class SeasonState(
    val animes: List<Anime> = emptyList(),
    val isLoading: Boolean = false,
    val year: Int = LocalDate.now().year,
    val season: String = "Fall",
    val error: UiText? = null
)