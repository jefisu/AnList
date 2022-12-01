package com.jefisu.anlist.data.dto.jikan_moe.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MangaReview(
    @SerialName("mal_id") val malId: Int,
    val url: String,
    val type: String,
    val reactions: Reactions,
    val date: String,
    val review: String,
    val score: Int,
    val tags: List<String>,
    @SerialName("is_spoiler") val isSpoiler: Boolean,
    @SerialName("is_preliminary") val isPreliminary: Boolean,
    @SerialName("chapters_read") val chaptersRead: Boolean?,
    val user: User
)