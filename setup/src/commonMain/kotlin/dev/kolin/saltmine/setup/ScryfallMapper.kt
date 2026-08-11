package dev.kolin.saltmine.setup

import dev.kolin.saltmine.core.domain.Commander
import dev.kolin.saltmine.core.domain.CommanderImage
import dev.kolin.saltmine.core.domain.MTGColor
import dev.kolin.saltmine.setup.response.ScryfallCardResponse
import dev.kolin.saltmine.setup.response.ScryfallResponse
import dev.zacsweers.metro.Inject

@Inject
internal class ScryfallMapper {
    fun toDomain(response: ScryfallResponse?): Result<List<Commander>> {
        if (response == null) return Result.failure(IllegalStateException("response is null"))
        if (response.data == null) return Result.failure(IllegalStateException("data is null: $response"))

        val commanders = response.data.mapNotNull { card ->
            toDomain(card)
                .onFailure { println("Error mapping card: $card") }
                .getOrNull()
        }

        return Result.success(commanders)
    }

    private fun toDomain(card: ScryfallCardResponse): Result<Commander> {
        if (card.name == null) return Result.failure(IllegalStateException("name is null: $card"))
        if (card.typeLine == null) return Result.failure(IllegalStateException("typeLine is null: $card"))
        if (card.colorIdentity == null) return Result.failure(IllegalStateException("colorIdentity is null: $card"))
        if (card.imageUrls == null) return Result.failure(IllegalStateException("imageUrls is null: $card"))

        val types = card.typeLine.split(" — ")[1].split(' ')
        val colorIdentity = card.colorIdentity.mapNotNull { it.toColor() }

        val commander = Commander(
            name = card.name,
            types = types,
            identity = colorIdentity,
            images = CommanderImage(
                small = card.imageUrls.small!!,
                normal = card.imageUrls.normal!!,
                large = card.imageUrls.large!!,
                thumb = card.imageUrls.thumb!!,
            ),
        )

        return Result.success(commander)
    }

    private fun String.toColor(): MTGColor? = when (this) {
        "W" -> MTGColor.WHITE
        "U" -> MTGColor.BLUE
        "B" -> MTGColor.BLACK
        "R" -> MTGColor.RED
        "G" -> MTGColor.GREEN
        "C" -> MTGColor.COLORLESS
        else -> null
    }
}
