package com.jefisu.anlist.data.repository

import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.core.util.requestCatch
import com.jefisu.anlist.data.AnimeConstants
import com.jefisu.anlist.data.dto.jikan_moe.AnimeResponse
import com.jefisu.anlist.data.dto.jikan_moe.character.CharactersResponse
import com.jefisu.anlist.data.dto.jikan_moe.recommendations.RecommendationsResponse
import com.jefisu.anlist.data.dto.jikan_moe.review.ReviewResponse
import com.jefisu.anlist.data.dto.jikan_moe.search.AnimeData
import com.jefisu.anlist.data.dto.jikan_moe.search.SearchResponse
import com.jefisu.anlist.data.dto.kitsu.KitsuResponse
import com.jefisu.anlist.domain.model.Anime
import com.jefisu.anlist.domain.model.Character
import com.jefisu.anlist.domain.model.Recommendation
import com.jefisu.anlist.domain.model.Review
import com.jefisu.anlist.domain.model.mapper.toAnime
import com.jefisu.anlist.domain.model.mapper.toCharacter
import com.jefisu.anlist.domain.model.mapper.toRecommendation
import com.jefisu.anlist.domain.model.mapper.toReview
import com.jefisu.anlist.domain.repository.AnimeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class AnimeRepositoryImpl(
    private val client: HttpClient
) : AnimeRepository {

    override suspend fun getAnimeById(malId: Int): Resource<Anime> {
        return requestCatch {
            client
                .get("${AnimeConstants.BASE_URL}/anime/$malId")
                .body<AnimeResponse>()
                .data.toAnime()
        }
    }

    override suspend fun searchAnime(name: String): Resource<List<Anime>> {
        return requestCatch {
            client
                .get("${AnimeConstants.BASE_URL}/anime?q=$name")
                .body<SearchResponse>()
                .data.map { it.toAnime() }
        }
    }

    override suspend fun getCharacters(malId: Int): Resource<List<Character>> {
        return requestCatch {
            client
                .get("${AnimeConstants.BASE_URL}/anime/$malId/characters")
                .body<CharactersResponse>()
                .data.map { it.toCharacter() }
        }
    }

    override suspend fun getReviews(malId: Int): Resource<List<Review>> {
        return requestCatch {
            client
                .get("${AnimeConstants.BASE_URL}/anime/$malId/reviews")
                .body<ReviewResponse>()
                .data.map { it.toReview() }
        }
    }

    override suspend fun getRecommendations(): Resource<List<Recommendation>> {
        return requestCatch {
            buildList {
                client
                    .get("${AnimeConstants.BASE_URL}/recommendations/anime")
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
                .body<SearchResponse>()
                .data.map { it.toAnime() }
        }
    }

    override suspend fun getTop(): Resource<List<Anime>> {
        return requestCatch {
            client
                .get("${AnimeConstants.BASE_URL}/top/anime")
                .body<SearchResponse>()
                .data.map { it.toAnime() }
        }
    }

    override suspend fun getImageBackground(name: String): String {
        return client.get {
            url("https://kitsu.io/api/edge/anime?fields[anime]=canonicalTitle,coverImage&filter[text]=$name")
        }.body<KitsuResponse>()
            .data.firstOrNull { it.attributes.canonicalTitle.contains(name, true) }
            ?.attributes?.coverImage?.original.orEmpty()
    }
}