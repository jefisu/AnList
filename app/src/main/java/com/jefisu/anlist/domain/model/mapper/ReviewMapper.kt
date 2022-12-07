package com.jefisu.anlist.domain.model.mapper

import com.jefisu.anlist.data.dto.jikan_moe.review.AnimeReview
import com.jefisu.anlist.data.dto.jikan_moe.review.UserDto
import com.jefisu.anlist.domain.model.Review
import com.jefisu.anlist.domain.model.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private fun formatDate(value: String): String {
    val parsedDate = LocalDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    return DateTimeFormatter.ofPattern("MM/dd/yyyy")
        .format(parsedDate)
}

fun UserDto.toUser() = User(
    username = username,
    image = images.jpg.imageUrl
)

fun AnimeReview.toReview() = Review(
    malId = malId,
    type = type,
    date = formatDate(date),
    review = review,
    user = user.toUser()
)