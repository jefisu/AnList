package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.search.AnimeDto
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.presentation.detail.util.getGenresImage

fun AnimeDto.toAnime() = Anime(
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
    rate = score ?: 0f,
    premiered = "$season $year",
    studios = studios.map { it.name },
    genres = getGenresImage(genres.map { it.name })
)