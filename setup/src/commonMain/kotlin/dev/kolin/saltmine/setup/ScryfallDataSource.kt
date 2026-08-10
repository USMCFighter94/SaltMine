package dev.kolin.saltmine.setup

import dev.kolin.saltmine.core.domain.Commander
import dev.kolin.saltmine.setup.response.ScryfallResponse
import dev.zacsweers.metro.Inject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

@Inject
public class ScryfallDataSource internal constructor(
    private val client: HttpClient,
    private val scryfallMapper: ScryfallMapper,
) {

    public suspend fun fetchCommanders(): List<Commander> {
        val response = client.get(BASE) {
            url {
                parameters.append(QUERY_PARAM_KEY, QUERY_LEGAL_COMMANDERS)
            }
        }.body<ScryfallResponse>()

        val domainResult = scryfallMapper.toDomain(response)
            .onFailure { println("Error fetching commanders: $it") }

        return domainResult.getOrNull().orEmpty()
    }

    public companion object {
        private const val BASE = "https://api.scryfall.com/cards/search"
        private const val QUERY_PARAM_KEY = "q"
        private const val QUERY_LEGAL_COMMANDERS = "legal:commander is:commander"
    }
}