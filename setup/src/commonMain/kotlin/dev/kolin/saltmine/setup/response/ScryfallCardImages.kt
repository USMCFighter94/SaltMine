package dev.kolin.saltmine.setup.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScryfallCardImages(
    @SerialName("small") val small: String?,
    @SerialName("normal") val normal: String?,
    @SerialName("large") val large: String?,
    @SerialName("png") val png: String?,
    @SerialName("art_crop") val artCrop: String?,
    @SerialName("border_crop") val borderCrop: String?,
    @SerialName("thumb") val thumb: String?,
    @SerialName("grid") val grid: String?,
    @SerialName("display") val display: String?,
    @SerialName("art") val art: String?,
    @SerialName("crop") val crop: String?,
)
