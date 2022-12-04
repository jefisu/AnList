package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.recommendations.Entry
import com.jefisu.anlist.domain.model.Recommendation

fun Entry.toRecommendation() = Recommendation(
    malId = malId,
    title = title,
    image = images.jpg.largeImageUrl.orEmpty()
)