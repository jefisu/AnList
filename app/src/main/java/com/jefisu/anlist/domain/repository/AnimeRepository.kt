package com.jefisu.anlist.domain.repository

import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.Character
import com.jefisu.anlist.domain.model.Recommendation
import com.jefisu.anlist.domain.model.Review

interface AnimeRepository {

    suspend fun getAnimeById(malId: Int): Resource<Anime>
    suspend fun searchAnime(name: String): Resource<List<Anime>>
    suspend fun getCharacters(malId: Int): Resource<List<Character>>
    suspend fun getReviews(malId: Int): Resource<List<Review>>
    suspend fun getRecommendations(): Resource<List<Recommendation>>
    suspend fun getAnimeBySeason(year: Int, season: String): Resource<List<Anime>>
    suspend fun getTop(): Resource<List<Anime>>
    suspend fun getImageBackground(name: String): String
}