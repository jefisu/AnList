package com.jefisu.anlist.presentation.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.presentation.destinations.DetailScreenDestination
import com.jefisu.anlist.ui.theme.DarkSlateBlue
import com.jefisu.anlist.ui.theme.defaultTextStyle
import com.ramcosta.composedestinations.spec.Direction

@Composable
fun AnimeByGenreItem(
    genre: String,
    allAnime: List<Anime>,
    onNavigate: (Direction) -> Unit
) {
    val animeThisGenre = allAnime
        .filter { it.genres.contains(genre) }
        .distinctBy { it.malId }
        .shuffled()

    Text(
        text = genre,
        style = defaultTextStyle,
        fontSize = 14.sp,
        color = DarkSlateBlue,
        modifier = Modifier.padding(start = 16.dp)
    )
    Spacer(modifier = Modifier.height(12.dp))
    LazyRow {
        itemsIndexed(animeThisGenre) { index, anime ->
            AnimeItem(
                anime = anime,
                onClick = { onNavigate(DetailScreenDestination(anime.malId)) },
                modifier = Modifier.padding(
                    start = if (index == 0) 16.dp else 0.dp
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}