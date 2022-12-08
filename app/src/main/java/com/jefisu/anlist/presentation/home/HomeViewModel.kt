package com.jefisu.anlist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.Recommendation
import com.jefisu.anlist.domain.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    private val isLoading = MutableStateFlow(false)
    private val topAiringAnime = MutableStateFlow(emptyList<Anime>())
    private val recommendationsAnime = MutableStateFlow(emptyList<Recommendation>())

    val state = combine(
        isLoading,
        topAiringAnime,
        recommendationsAnime
    ) { isLoading, topAiringAnime, recommendations ->
        HomeState(
            isLoading = isLoading,
            recommendationsAnime = recommendations,
            topAiringAnime = topAiringAnime
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState())

    init {
        loadingData()
    }

    fun loadingData() {
        viewModelScope.launch {
            isLoading.update { true }
            val topsAnime = async {
                val response = repository.getTop()
                if (response is Resource.Success) response.data.orEmpty() else emptyList()
            }
            val recommendationAnime = async {
                val response = repository.getRecommendations()
                if (response is Resource.Success) response.data.orEmpty() else emptyList()
            }
            topAiringAnime.update {
                topsAnime.await().filterIndexed { i, _ -> i < 5 }
            }
            recommendationsAnime.update {
                recommendationAnime.await()
            }
            isLoading.update { false }
        }
    }
}