package dev.kolin.saltmine.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

actual fun createPlatformHttpClient(): HttpClient =
    HttpClient(
        engineFactory = CIO,
        block = httpClientConfig(),
    )