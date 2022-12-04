package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.review.AnimeReview
import com.jefisu.anlist.data.dto.jikan_moe.review.MangaReview
import com.jefisu.anlist.data.dto.jikan_moe.review.UserDto
import com.jefisu.anlist.domain.model.Review
import com.jefisu.anlist.domain.model.User

fun UserDto.toUser() = User(
    username = username,
    image = images.jpg.imageUrl
)

fun AnimeReview.toReview() = Review(
    malId = malId,
    type = type,
    date = date,
    review = review,
    user = user.toUser()
)

fun MangaReview.toReview() = Review(
    malId = malId,
    type = type,
    date = date,
    review = review,
    user = user.toUser()
)