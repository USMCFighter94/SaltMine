package dev.kolin.saltmine.setup.response

internal fun createFakeScryfallResponse(
    obj: String? = null,
    totalCards: Int? = 0,
    hasMore: Boolean? = false,
    nextPage: String? = null,
    data: List<ScryfallCardResponse>? = emptyList(),
    warnings: List<String>? = null,
): ScryfallResponse = ScryfallResponse(
    obj = obj,
    totalCards = totalCards,
    hasMore = hasMore,
    nextPage = nextPage,
    data = data,
    warnings = warnings,
)
