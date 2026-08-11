package dev.kolin.saltmine.core.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

public expect fun createPlatformHttpClient(): HttpClient

public fun createHttpClient(): HttpClient = createPlatformHttpClient()

internal fun httpClientConfig(): HttpClientConfig<HttpClientEngineConfig>.() -> Unit = {
    expectSuccess = true
    install(ContentNegotiation) {
        json(
            Json {
                isLenient = true
                explicitNulls = false
                encodeDefaults = true
                ignoreUnknownKeys = true
            },
        )
    }
}
