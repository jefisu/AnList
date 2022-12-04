package com.jefisu.anlist.domain.model

data class Review(
    val malId: Int,
    val type: String,
    val date: String,
    val review: String,
    val user: User
)