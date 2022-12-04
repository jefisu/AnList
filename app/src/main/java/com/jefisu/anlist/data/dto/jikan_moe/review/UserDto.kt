package com.jefisu.anlist.data.dto.jikan_moe.review

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val url: String,
    val username: String,
    val images: Images
)