package dev.kolin.saltmine.setup.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScryfallCardResponse(
    @SerialName("object") val obj: String?,
    val id: String?,
    val name: String?,
    @SerialName("mana_cost") val manaCost: String?,
    val cmc: Double?,
    @SerialName("type_line") val typeLine: String?,
    @SerialName("oracle_text") val oracle: String?,
    val power: String?,
    val toughness: String?,
    val colors: List<String>?,
    @SerialName("color_identity") val colorIdentity: List<String>?,
    val set: String?,
    @SerialName("set_name") val setName: String?,
    val rarity: String?,
    @SerialName("image_uris") val imageUrls: ScryfallCardImages?,
)
