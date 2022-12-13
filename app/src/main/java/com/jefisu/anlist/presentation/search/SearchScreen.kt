package com.jefisu.anlist.presentation.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jefisu.anlist.core.presentation.StandardScreen
import com.jefisu.anlist.presentation.search.components.SearchTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    StandardScreen(
        title = {
            SearchTextField(
                text = state.query,
                onTextChange = viewModel::onTextChange,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigator = navigator,
        isLoading = state.isLoading,
        animes = state.searchResult,
        getRestOtherPages = viewModel::searchAnimes,
        error = state.error
    )
}