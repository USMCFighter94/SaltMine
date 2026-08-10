package dev.kolin.saltmine.setup.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ScryfallResponse(
    @SerialName("object") val obj: String?,
    @SerialName("total_cards") val totalCards: Int?,
    @SerialName("has_more") val hasMore: Boolean?,
    @SerialName("next_page") val nextPage: String?,
    val data: List<ScryfallCardResponse>?,
    val warnings: List<String>?,
)
