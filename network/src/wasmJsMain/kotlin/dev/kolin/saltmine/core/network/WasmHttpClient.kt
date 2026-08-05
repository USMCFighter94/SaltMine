package dev.kolin.saltmine.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js

public actual fun createPlatformHttpClient(): HttpClient =
    HttpClient(
        engineFactory = Js,
        block = httpClientConfig(),
    )
