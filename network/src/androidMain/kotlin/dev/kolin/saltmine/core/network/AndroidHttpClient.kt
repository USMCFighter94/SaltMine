package dev.kolin.saltmine.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

public actual fun createPlatformHttpClient(): HttpClient =
    HttpClient(
        engineFactory = OkHttp,
        block = httpClientConfig(),
    )
