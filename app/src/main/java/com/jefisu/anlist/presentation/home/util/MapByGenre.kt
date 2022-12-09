package com.jefisu.anlist.presentation.home.util

import com.jefisu.anlist.data.AnimeConstants
import com.jefisu.anlist.domain.model.Anime

fun Iterable<Anime>.mapByGenre(
    genres: List<String> = AnimeConstants.genres
): Map<String, List<Anime>> {
    return buildMap {
        genres.forEach { genre ->
            val animesThisGenre = this@mapByGenre.filterIndexed { index, anime ->
                anime.genres.contains(genre)
            }.distinctBy { it.malId }
            if (animesThisGenre.isNotEmpty()) {
                put(genre, animesThisGenre)
            }
        }
    }
}