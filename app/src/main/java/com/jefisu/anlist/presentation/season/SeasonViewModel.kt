package com.jefisu.anlist.presentation.season

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.core.util.UiText
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.repository.AnimeRepository
import com.jefisu.anlist.presentation.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel @Inject constructor(
    private val repository: AnimeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var page = 1
    private var totalAnimes = 0
    private val navArgs = savedStateHandle.navArgs<SeasonNavArgs>()

    private val animes = MutableStateFlow(emptyList<Anime>())
    private val isLoading = MutableStateFlow(false)
    private val error = MutableStateFlow<UiText?>(null)

    val state = combine(animes, isLoading, error) { animes, isLoading, error ->
        SeasonState(
            animes = animes.sortedByDescending { it.rate },
            isLoading = isLoading,
            year = navArgs.year,
            season = navArgs.season,
            error = error
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SeasonState())

    init {
        getSeason()
    }

    fun getSeason() {
        if (totalAnimes > 0 && animes.value.size == totalAnimes) {
            return
        }
        viewModelScope.launch {
            isLoading.update { true }
            val response = repository.getAnimeBySeason(navArgs.year, navArgs.season, page)
            when (response) {
                is Resource.Success -> {
                    animes.update { it + response.data?.items.orEmpty() }
                    totalAnimes = response.data?.totalItems ?: 0
                    page++
                }
                is Resource.Error -> {
                    error.update { response.uiText }
                    animes.update { emptyList() }
                }
            }
            isLoading.update { false }
        }
    }
}