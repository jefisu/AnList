package com.jefisu.anlist.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.repository.AnimeRepository
import com.jefisu.anlist.presentation.home.util.mapByGenre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    private var currentPage = 1

    private val isLoading = MutableStateFlow(false)
    private val progress = MutableStateFlow(0f)
    private val topAiringAnime = MutableStateFlow(emptyList<Anime>())

    val state = combine(
        isLoading,
        topAiringAnime,
        progress,
    ) { isLoading, topAiringAnime, progress ->
        val animesTopFive = topAiringAnime.filterIndexed { index, _ -> index < 5 }
        HomeState(
            progress = progress,
            isLoading = isLoading,
            topAiringAnime = animesTopFive,
            animesByGenre = topAiringAnime
                .dropWhile { animesTopFive.contains(it) }
                .mapByGenre()
                .filterValues { it.size == 10 }
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState())

    init {
        viewModelScope.launch {
            isLoading.update { true }
            repeat(5) {
                loadingData()
                delay(1000L)
            }
            isLoading.update { false }
            delay(300L)
            progress.update { 0f }
        }
    }

    fun loadingData() {
        viewModelScope.launch {
            val topsAnime = async {
                val response = repository.getTop(currentPage)
                if (response is Resource.Success) {
                    currentPage++
                    response.data.orEmpty()
                } else {
                    emptyList()
                }
            }
            topAiringAnime.update { it + topsAnime.await() }
            progress.update {
                if (it == 100f) {
                    return@update it
                }
                it + topsAnime.await().size.toFloat()
            }
        }
    }
}