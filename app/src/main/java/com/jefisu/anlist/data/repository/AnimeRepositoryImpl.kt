package com.jefisu.anlist.data.repository

import com.jefisu.anlist.BuildConfig
import com.jefisu.anlist.R
import com.jefisu.anlist.core.util.Resource
import com.jefisu.anlist.core.util.UiText
import com.jefisu.anlist.core.util.requestCatch
import com.jefisu.anlist.data.AnimeConstants
import com.jefisu.anlist.data.dto.jikan_moe.AnimeResponse
import com.jefisu.anlist.data.dto.jikan_moe.character.CharactersResponse
import com.jefisu.anlist.data.dto.jikan_moe.recommendations.RecommendationsResponse
import com.jefisu.anlist.data.dto.jikan_moe.review.ReviewResponse
import com.jefisu.anlist.data.dto.jikan_moe.search.SearchResponse
import com.jefisu.anlist.data.dto.the_movie_db.TheMovieResponse
import com.jefisu.anlist.domain.model.*
import com.jefisu.anlist.domain.model.mapper.*
import com.jefisu.anlist.domain.repository.AnimeRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import java.io.IOException

class AnimeRepositoryImpl(
    private val client: HttpClient
) : AnimeRepository {

    override suspend fun getAnimeById(malId: Int): Resource<Anime> {
        return requestCatch {
            val response = client.get("${AnimeConstants.BASE_URL}/anime/$malId")
            val anime = response.body<AnimeResponse>().data.toAnime()
            val image = getImageBackground(anime.titleEnglish)
            anime.copy(imageBackground = image.ifBlank { anime.poster })
        }
    }

    override suspend fun searchAnime(name: String): Resource<List<Anime>> {
        if (name.isEmpty()) {
            return Resource.Success(emptyList())
        }
        return try {
            val response = client.get("${AnimeConstants.BASE_URL}/anime?q=$name")
                .body<SearchResponse>()
                .toDataResponse()
                .items
            if (response.isEmpty()) {
                return Resource.Error(UiText.StringResource(R.string.no_results_found_try_searching_again))
            }
            Resource.Success(response)
        } catch (_: ResponseException) {
            Resource.Error(
                UiText.StringResource(R.string.oops_something_went_wrong)
            )
        } catch (_: IOException) {
            Resource.Error(
                UiText.StringResource(R.string.error_couldnt_reach_server)
            )
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

    override suspend fun getAnimeBySeason(
        year: Int,
        season: String,
        page: Int
    ): Resource<DataResponse<Anime>> {
        return requestCatch {
            client
                .get("${AnimeConstants.BASE_URL}/seasons/$year/$season?page=$page")
                .body<SearchResponse>()
                .toDataResponse()
        }
    }

    override suspend fun getTop(page: Int): Resource<List<Anime>> {
        return requestCatch {
            val response = client.get("${AnimeConstants.BASE_URL}/top/anime?page=$page")
            response
                .body<SearchResponse>()
                .toDataResponse()
                .items
        }
    }

    override suspend fun getImageBackground(name: String): String {
        val response = client.get {
            url("https://api.themoviedb.org/3/search/tv")
            parameter("api_key", BuildConfig.API_KEY)
            parameter("query", name)
        }
        val imageKey = response.body<TheMovieResponse>()
            .results.firstOrNull { it.name.contains(name, true) }
            ?.backdropPath ?: return ""
        return "https://image.tmdb.org/t/p/w500/$imageKey"
    }
}