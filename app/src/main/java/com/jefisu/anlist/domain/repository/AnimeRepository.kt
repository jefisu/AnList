package com.jefisu.anlist.domain.repository

import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.Character
import com.jefisu.anlist.domain.model.Recommendation
import com.jefisu.anlist.domain.model.Review

interface AnimeRepository {
    suspend fun <T> search(name: String, type: String): Resource<List<T>>
    suspend fun getCharacters(malId: Int, type: String): Resource<List<Character>>
    suspend fun getReviews(malId: Int, type: String): Resource<List<Review>>
    suspend fun getRecommendations(type: String): Resource<List<Recommendation>>
    suspend fun getAnimeBySeason(year: Int, season: String): Resource<List<Anime>>
    suspend fun <T> getTop(type: String): Resource<List<T>>
    suspend fun getImageBackground(name: String, type: String): String
}