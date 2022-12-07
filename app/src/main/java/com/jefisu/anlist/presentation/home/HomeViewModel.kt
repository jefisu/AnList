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
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    private val isLoading = MutableStateFlow(false)
    private val topAiringAnime = MutableStateFlow(emptyList<Anime>())
    private val recommendationsAnime = MutableStateFlow(emptyList<Recommendation>())
    private val imagesBackground = MutableStateFlow(emptyMap<Int, String>())

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
            isLoading.value = true
            val topsAnime = async {
                val response = repository.getTop()
                if (response is Resource.Success) response.data.orEmpty() else emptyList()
            }
            val recommendationAnime = async {
                val response = repository.getRecommendations()
                if (response is Resource.Success) response.data.orEmpty() else emptyList()
            }
            topAiringAnime.value = topsAnime.await().filterIndexed { i, _ -> i < 5 }
            getImageBackground()
            recommendationsAnime.value = recommendationAnime.await()
            isLoading.value = false
        }
    }

    private fun getImageBackground() {
        viewModelScope.launch {
            topAiringAnime.value.forEach {
                val image = repository.getImageBackground(it.name)
                imagesBackground.value += Pair(it.malId, image)
            }
            topAiringAnime.value = topAiringAnime.value
                .toMutableList()
                .apply {
                    replaceAll {
                        if (imagesBackground.value.containsKey(it.malId)) {
                            it.copy(imageBackground = imagesBackground.value[it.malId])
                        } else it
                    }
                }
        }
    }
}