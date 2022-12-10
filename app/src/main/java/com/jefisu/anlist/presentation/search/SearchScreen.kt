package com.jefisu.anlist.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jefisu.anlist.R
import com.jefisu.anlist.core.presentation.CustomCard
import com.jefisu.anlist.core.presentation.StandardScreen
import com.jefisu.anlist.core.util.isOdd
import com.jefisu.anlist.presentation.destinations.DetailScreenDestination
import com.jefisu.anlist.presentation.search.components.SearchTextField
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.defaultTextStyle
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
        isLoading = state.isLoading,
        navigationIcon = Icons.Default.ArrowBackIosNew,
        navigationOnClick = navigator::navigateUp,
        title = {
            SearchTextField(
                text = state.query,
                onTextChange = viewModel::searchAnimes,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) {
        state.error?.let { error ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = R.drawable.pikachu_looking,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = error.asString(),
                    style = defaultTextStyle,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = DarkSlateBlue,
                    modifier = Modifier.graphicsLayer {
                        translationY = -16.dp.toPx()
                    }
                )
            }
            return@StandardScreen
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            val lastIndex = state.searchResult.lastIndex
            val penultimateIndex = lastIndex - 1
            val isOdd = isOdd(state.searchResult.size)
            itemsIndexed(state.searchResult) { index, anime ->
                CustomCard(
                    anime = anime,
                    size = DpSize(width = 181.dp, height = 101.dp),
                    onClick = {
                        navigator.navigate(DetailScreenDestination(anime.malId))
                    },
                    paddingValues = PaddingValues(
                        top = if (index in 0..1) 8.dp else 0.dp,
                        bottom = when {
                            (index == lastIndex && isOdd) -> 8.dp
                            (!isOdd && index in penultimateIndex..lastIndex) -> 8.dp
                            else -> 0.dp
                        }
                    )
                )
            }
        }
    }
}