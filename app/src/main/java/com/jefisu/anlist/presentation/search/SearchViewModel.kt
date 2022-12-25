package com.jefisu.anlist.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.core.util.UiText
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    private var page = 1
    private var totalAnimes = 0

    private val query = MutableStateFlow("")
    private val searchResult = MutableStateFlow(emptyList<Anime>())
    private val isLoading = MutableStateFlow(false)
    private val error = MutableStateFlow<UiText?>(null)

    private var searchJob: Job? = null

    val state =
        combine(query, searchResult, isLoading, error) { query, searchResult, isLoading, error ->
            SearchState(
                query = query,
                searchResult = searchResult.sortedByDescending { it.rate },
                isLoading = isLoading,
                error = error
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SearchState())

    fun onTextChange(value: String) {
        query.update { value }
        searchResult.update { emptyList() }
        if (value.isEmpty() || value.isBlank()) {
            error.update { null }
            return
        }
        searchAnimes()
    }

    fun searchAnimes() {
        if (totalAnimes > 0 && searchResult.value.size == totalAnimes) {
            return
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            isLoading.update { true }
            delay(300L)
            val response = repository.searchAnime(query.value, page)
            when (response) {
                is Resource.Success -> {
                    searchResult.update { it + response.data?.items.orEmpty() }
                    totalAnimes = response.data?.totalItems ?: 0
                    page++
                    error.update { null }
                }
                is Resource.Error -> {
                    error.update { response.uiText }
                }
            }
            isLoading.update { false }
        }
    }
}