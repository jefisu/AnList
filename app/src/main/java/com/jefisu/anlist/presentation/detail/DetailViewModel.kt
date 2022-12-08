package com.jefisu.anlist.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.Character
import com.jefisu.anlist.domain.model.Review
import com.jefisu.anlist.domain.repository.AnimeRepository
import com.jefisu.anlist.presentation.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: AnimeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val navArgs = savedStateHandle.navArgs<DetailNavArg>()

    private val animeFlow = MutableStateFlow<Anime?>(null)
    private val isLoading = MutableStateFlow(false)
    private val reviews = MutableStateFlow(emptyList<Review>())
    private val characters = MutableStateFlow(emptyList<Character>())

    val state =
        combine(
            isLoading,
            animeFlow,
            reviews,
            characters
        ) { isLoading, anime, reviews, characters ->
            DetailState(
                anime = anime,
                reviews = reviews,
                characters = characters,
                isLoading = isLoading
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DetailState())

    init {
        getAnime()
    }

    private fun getAnime() {
        isLoading.update { true }
        viewModelScope.launch {
            val response = repository.getAnimeById(navArgs.malId)
            animeFlow.update {
                if (response is Resource.Success) response.data else null
            }
            getAdditionalInfo()
            isLoading.update { false }
        }
    }

    private fun getAdditionalInfo() {
        val anime = animeFlow.value ?: return
        viewModelScope.launch {
            launch {
                val response = repository.getReviews(anime.malId)
                reviews.update {
                    if (response is Resource.Success) response.data.orEmpty() else emptyList()
                }
            }
            launch {
                val response = repository.getCharacters(anime.malId)
                characters.update {
                    if (response is Resource.Success) response.data.orEmpty() else emptyList()
                }
            }
        }
    }
}