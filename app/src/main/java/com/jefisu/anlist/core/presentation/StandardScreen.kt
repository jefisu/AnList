package com.jefisu.anlist.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jefisu.anlist.R
import com.jefisu.anlist.core.util.UiText
import com.jefisu.anlist.core.util.isOdd
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun StandardScreen(
    navigator: DestinationsNavigator,
    title: @Composable () -> Unit,
    isLoading: Boolean,
    animes: List<Anime>,
    getRestOtherPages: () -> Unit,
    error: UiText?
) {
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
            title()
        }
        if (isLoading && animes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingGif(content = R.drawable.pikachu_walking)
            }
            return@Column
        }
        error?.let {
            ErrorScreen(error = it)
            return@Column
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            val lastIndex = animes.lastIndex
            val penultimateIndex = lastIndex - 1
            val isOdd = isOdd(animes.size)
            itemsIndexed(animes) { index, anime ->
                val size = animes.size
                if (index >= size - 1 && !isLoading) {
                    getRestOtherPages()
                }
                CustomCard(
                    anime = anime,
                    onClick = navigator::navigate,
                    paddingValues = PaddingValues(
                        top = if (index in 0..1) 8.dp else 0.dp,
                        bottom = when {
                            (index == lastIndex && isOdd) -> 8.dp
                            (!isOdd && index in penultimateIndex..lastIndex) -> 8.dp
                            else -> 0.dp
                        }
                    ),
                    imageModifier = Modifier
                        .height(101.dp)
                )
            }
        }
        if (isLoading && animes.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            CircularProgressIndicator(
                color = DarkSlateBlue,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )
        }
    }
}