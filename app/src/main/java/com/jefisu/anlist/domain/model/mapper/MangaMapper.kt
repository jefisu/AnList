package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.search.MangaDto
import com.jefisu.anlist.domain.model.Manga
import com.jefisu.anlist.presentation.detail.util.getGenresImage

fun MangaDto.toManga() = Manga(
    malId = malId,
    malUrl = url,
    name = title,
    synopsis = synopsis.orEmpty(),
    poster = images.jpg.largeImageUrl.orEmpty(),
    type = type.orEmpty(),
    chapters = chapters ?: 0,
    volumes = volumes ?: 0,
    status = status.orEmpty(),
    rate = score ?: 0f,
    authors = authors.map { it.name },
    genres = getGenresImage(genres.map { it.name })
)