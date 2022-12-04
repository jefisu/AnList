package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDto(
    @SerialName("mal_id") val malId: Int,
    val url: String,
    val images: Images,
    val trailer: Trailer,
    val approved: Boolean,
    val titles: List<Title>,
    val title: String,
    @SerialName("title_english") val titleEnglish: String?,
    @SerialName("title_japanese") val titleJapanese: String?,
    @SerialName("title_synonyms") val titleSynonyms: List<String>,
    val type: String?,
    val source: String?,
    val episodes: Int?,
    val status: String?,
    val airing: Boolean,
    val aired: Aired,
    val duration: String?,
    val rating: String?,
    val score: Float?,
    @SerialName("scored_by")val scoredBy: Int?,
    val rank: Int?,
    val popularity: Int?,
    val members: Int?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val season: String?,
    val year: Int?,
    val broadcast: Broadcast,
    val producers: List<DataExtension>,
    val licensors: List<DataExtension>,
    val studios: List<DataExtension>,
    val genres: List<DataExtension>,
    @SerialName("explicit_genres")val explicitGenres: List<String>,
    val demographics: List<DataExtension>,
    val themes: List<DataExtension>
)