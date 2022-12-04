package com.jefisu.anlist.domain.model

data class Anime(
    val malId: Int,
    val malUrl: String,
    val name: String,
    val synopsis: String,
    val poster: String,
    val trailerYoutubeId: String,
    val type: String,
    val source: String,
    val episodes: Int,
    val status: String,
    val duration: String,
    val rate: Float,
    val premiered: String,
    val studios: List<String>,
    val genres: List<Int>
)