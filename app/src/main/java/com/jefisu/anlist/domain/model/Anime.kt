package com.jefisu.anlist.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val malId: Int,
    val malUrl: String,
    val title: String,
    val titleEnglish: String,
    val synopsis: String,
    val poster: String,
    val trailerYoutubeId: String,
    val type: String,
    val source: String,
    val episodes: Int,
    val status: String,
    val duration: String,
    val rate: String,
    val premiered: String,
    val studios: List<String>,
    val genres: List<String>,
    val imageBackground: String? = null
) : Parcelable