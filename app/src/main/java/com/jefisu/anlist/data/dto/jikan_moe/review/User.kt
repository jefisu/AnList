package com.jefisu.anlist.data.dto.jikan_moe.review

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val url: String,
    val username: String,
    val images: Images
)