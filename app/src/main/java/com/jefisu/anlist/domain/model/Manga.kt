package com.jefisu.anlist.domain.model

data class Manga(
    val malId: Int,
    val malUrl: String,
    val name: String,
    val synopsis: String,
    val poster: String,
    val type: String,
    val chapters: Int,
    val volumes: Int,
    val status: String,
    val rate: Float,
    val authors: List<String>,
    val genres: List<Int>
)