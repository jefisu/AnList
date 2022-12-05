package com.jefisu.anlist.data.repository

import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.core.util.requestCatch
import com.jefisu.anlist.data.AnimeConstants
import com.jefisu.anlist.data.dto.jikan_moe.character.AnimeCharacters
import com.jefisu.anlist.data.dto.jikan_moe.character.MangaCharacters
import com.jefisu.anlist.data.dto.jikan_moe.recommendations.RecommendationsResponse
import com.jefisu.anlist.data.dto.jikan_moe.review.AnimeReviews
import com.jefisu.anlist.data.dto.jikan_moe.review.MangaReviews
import com.jefisu.anlist.data.dto.jikan_moe.search.AnimeDto
import com.jefisu.anlist.data.dto.jikan_moe.search.MangaDto
import com.jefisu.anlist.data.dto.jikan_moe.search.SearchResponse
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.Character
import com.jefisu.anlist.domain.model.Recommendation
import com.jefisu.anlist.domain.model.Review
import com.jefisu.anlist.domain.model.mapper.toAnime
import com.jefisu.anlist.domain.model.mapper.toCharacter
import com.jefisu.anlist.domain.model.mapper.toManga
import com.jefisu.anlist.domain.model.mapper.toRecommendation
import com.jefisu.anlist.domain.model.mapper.toReview
import com.jefisu.anlist.domain.repository.AnimeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AnimeRepositoryImpl(
    private val client: HttpClient
) : AnimeRepository {

    override suspend fun <T> search(name: String, type: String): Resource<List<T>> {
        return requestCatch {
            if (type == "anime") {
                client
                    .get("${AnimeConstants.BASE_URL}/anime?q=$name")
                    .body<SearchResponse<AnimeDto>>()
                    .data.map { it.toAnime() }
            } else {
                client
                    .get("${AnimeConstants.BASE_URL}/manga?q=$name")
                    .body<SearchResponse<MangaDto>>()
                    .data.map { it.toManga() }
            } as List<T>
        }
    }

    override suspend fun getCharacters(malId: Int, type: String): Resource<List<Character>> {
        return requestCatch {
            if (type == "anime") {
                client
                    .get("${AnimeConstants.BASE_URL}/anime/$malId/characters")
                    .body<AnimeCharacters>()
                    .data.map { it.character.toCharacter() }
            } else {
                client
                    .get("${AnimeConstants.BASE_URL}/manga/$malId/characters")
                    .body<MangaCharacters>()
                    .data.map { it.character.toCharacter() }
            }
        }
    }

    override suspend fun getReviews(malId: Int, type: String): Resource<List<Review>> {
        return requestCatch {
            if (type == "anime") {
                client
                    .get("${AnimeConstants.BASE_URL}/anime/$malId/reviews")
                    .body<AnimeReviews>()
                    .data.map { it.toReview() }
            } else {
                client
                    .get("${AnimeConstants.BASE_URL}/manga/$malId/reviews")
                    .body<MangaReviews>()
                    .data.map { it.toReview() }
            }
        }
    }

    override suspend fun getRecommendations(type: String): Resource<List<Recommendation>> {
        return requestCatch {
            buildList {
                client
                    .get("${AnimeConstants.BASE_URL}/recommendations/$type")
                    .body<RecommendationsResponse>().recommendations
                    .forEach { recommendationPerUser ->
                        addAll(recommendationPerUser.entry.map { it.toRecommendation() })
                    }
            }
        }
    }

    override suspend fun getAnimeBySeason(year: Int, season: String): Resource<List<Anime>> {
        return requestCatch {
            client
                .get("${AnimeConstants.BASE_URL}/seasons/$year/$season")
                .body<SearchResponse<AnimeDto>>()
                .data.map { it.toAnime() }
        }
    }

    override suspend fun <T> getTop(type: String): Resource<List<T>> {
        return requestCatch {
            if (type == "anime") {
                client
                    .get("${AnimeConstants.BASE_URL}/top/anime")
                    .body<SearchResponse<AnimeDto>>()
                    .data.map { it.toAnime() }
            } else {
                client
                    .get("${AnimeConstants.BASE_URL}/top/manga")
                    .body<SearchResponse<MangaDto>>()
                    .data.map { it.toManga() }
            } as List<T>
        }
    }
}