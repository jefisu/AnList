package com.jefisu.anlist.core.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.jefisu.anlist.core.util.isOdd
import com.jefisu.anlist.domain.model.Anime

@Composable
fun CustomVerticalGrid(
    animes: List<Anime>,
    onNavigate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    searchMoreAnimes: (Int) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        val lastIndex = animes.lastIndex
        val penultimateIndex = lastIndex - 1
        val isOdd = isOdd(animes.size)
        itemsIndexed(animes) { index, anime ->
            searchMoreAnimes(index)
            CustomCard(
                anime = anime,
                size = DpSize(width = 181.dp, height = 101.dp),
                onClick = { onNavigate(anime.malId) },
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