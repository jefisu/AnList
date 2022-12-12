package com.jefisu.anlist.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jefisu.anlist.R
import com.jefisu.anlist.core.presentation.CustomVerticalGrid
import com.jefisu.anlist.core.presentation.ErrorScreen
import com.jefisu.anlist.core.presentation.LoadingGif
import com.jefisu.anlist.presentation.destinations.DetailScreenDestination
import com.jefisu.anlist.presentation.search.components.SearchTextField
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(DarkSlateBlue)
        ) {
            IconButton(onClick = navigator::navigateUp) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            SearchTextField(
                text = state.query,
                onTextChange = viewModel::searchAnimes,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingGif(content = R.drawable.pikachu_walking)
            }
            return@Column
        }
        state.error?.let {
            ErrorScreen(error = it)
            return@Column
        }
        CustomVerticalGrid(
            animes = state.searchResult,
            onNavigate = { malId ->
                navigator.navigate(DetailScreenDestination(malId))
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}