package com.jefisu.anlist.domain.repository

import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.domain.model.*

interface AnimeRepository {

    suspend fun getAnimeById(malId: Int): Resource<Anime>
    suspend fun searchAnime(name: String, page: Int): Resource<DataResponse<Anime>>
    suspend fun getCharacters(malId: Int): Resource<List<Character>>
    suspend fun getReviews(malId: Int): Resource<List<Review>>
    suspend fun getRecommendations(): Resource<List<Recommendation>>
    suspend fun getAnimeBySeason(year: Int, season: String, page: Int): Resource<DataResponse<Anime>>
    suspend fun getTop(page: Int): Resource<List<Anime>>
    suspend fun getImageBackground(name: String): String
}