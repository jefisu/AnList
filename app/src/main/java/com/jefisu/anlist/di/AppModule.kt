package com.jefisu.anlist.di

import com.jefisu.anlist.data.repository.AnimeRepositoryImpl
import com.jefisu.anlist.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient(Android) {
        install(ContentNegotiation) { json() }
        install(Logging) { level = LogLevel.ALL }
        expectSuccess = true
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(client: HttpClient): AnimeRepository {
        return AnimeRepositoryImpl(client)
    }
}