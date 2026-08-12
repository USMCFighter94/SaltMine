package dev.kolin.saltmine.core.domain

public data class Game(
    val id: Long,
    val name: String,
    val players: Int,
    val format: Format,
)
