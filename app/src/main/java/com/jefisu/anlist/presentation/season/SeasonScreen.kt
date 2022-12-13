package com.jefisu.anlist.presentation.season

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jefisu.anlist.core.presentation.StandardScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(
    navArgsDelegate = SeasonNavArgs::class
)
@Composable
fun SeasonScreen(
    navigator: DestinationsNavigator,
    viewModel: SeasonViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    StandardScreen(
        title = {
            Text(
                text = "${state.season} ${state.year}",
                fontSize = 18.sp,
                color = Color.White
            )
        },
        navigator = navigator,
        isLoading = state.isLoading,
        animes = state.animes,
        getRestOtherPages = viewModel::getSeason,
        error = state.error
    )
}