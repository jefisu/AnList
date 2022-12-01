package com.jefisu.anlist.data.dto.jikan_moe.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Manga(
    @SerialName("mal_id") val malId: Int,
    val url: String,
    val images: Images,
    val approved: Boolean,
    val titles: List<Title>,
    val title: String,
    @SerialName("title_english") val titleEnglish: String?,
    @SerialName("title_japanese") val titleJapanese: String?,
    @SerialName("title_synonyms") val titleSynonyms: List<String>,
    val type: String?,
    val chapters: Int?,
    val volumes: Int?,
    val status: String?,
    val publishing: String?,
    val published: Aired?,
    val score: Float?,
    val scored: Float?,
    @SerialName("scored_by") val scoredBy: Int?,
    val rank: Int?,
    val popularity: Int?,
    val members: Int?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val authors: List<DataExtension>,
    val serializations: List<DataExtension>,
    val genres: List<DataExtension>,
    @SerialName("explicit_genres") val explicitGenres: List<String>,
    val themes: List<DataExtension>,
    val demographics: List<DataExtension>
)
