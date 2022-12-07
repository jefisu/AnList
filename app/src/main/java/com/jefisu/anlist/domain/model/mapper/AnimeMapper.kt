package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.search.AnimeData
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.presentation.detail.util.getGenresImage

fun AnimeData.toAnime() = Anime(
    malId = malId,
    malUrl = url,
    name = title,
    synopsis = synopsis.orEmpty(),
    poster = images.jpg.largeImageUrl.orEmpty(),
    trailerYoutubeId = trailer.youtubeId.orEmpty(),
    source = source.orEmpty(),
    type = type.orEmpty(),
    episodes = episodes ?: 0,
    status = status.orEmpty(),
    duration = duration.orEmpty(),
    rate = score?.let { String.format("%.2f", it) } ?: "No rating",
    premiered = "${season?.replaceFirstChar { it.titlecase() }} $year",
    studios = studios.map { it.name },
    genres = getGenresImage(genres.map { it.name })
)