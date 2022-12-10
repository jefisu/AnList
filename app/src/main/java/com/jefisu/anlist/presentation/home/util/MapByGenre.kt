package com.jefisu.anlist.presentation.home.util

import com.jefisu.anlist.data.AnimeConstants
import com.jefisu.anlist.domain.model.Anime

fun Iterable<Anime>.mapByGenre(
    limit: Int = 10,
    genres: List<String> = AnimeConstants.genres
): Map<String, List<Anime>> {
    return buildMap {
        genres.forEach { genre ->
            val animesThisGenre = this@mapByGenre
                .filter { it.genres.contains(genre) }
                .distinctBy { it.malId }
                .filterIndexed { index, _ -> index < limit }
            if (animesThisGenre.isNotEmpty()) {
                put(genre, animesThisGenre)
            }
        }
    }
}