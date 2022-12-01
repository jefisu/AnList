package com.jefisu.anlist.data.dto.jikan_moe.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reactions(
    val overall: Int,
    val nice: Int,
    @SerialName("love_it") val loveIt: Int,
    val funny: Int,
    val confusing: Int,
    val informative: Int,
    @SerialName("well_written")val wellWritten: Int,
    val creative: Int
)