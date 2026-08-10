package dev.kolin.saltmine.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

public actual fun createPlatformHttpClient(): HttpClient = HttpClient(
    engineFactory = Darwin,
    block = httpClientConfig(),
)
