package com.jefisu.anlist.presentation.detail.util

data class Anime(
    val name: String,
    val synopsis: String,
    val rate: Float,
    val eps: Int,
    val episodeDuration: String,
    val premiered: String,
    val studio: String,
    val characters: List<Character>,
    val reviews: List<Review>,
    val genres: List<String>,
    val imageBackground: Int,
    val poster: Int
)