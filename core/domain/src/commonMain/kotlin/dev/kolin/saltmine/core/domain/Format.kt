package dev.kolin.saltmine.core.domain

sealed interface Format {
    val name: String
    val minPlayerCount: Int
    val maxPlayerCount: Int
    val startingLife: Int

    data object Standard : Format {
        override val name: String = "Standard"
        override val minPlayerCount: Int = 2
        override val maxPlayerCount: Int = 2
        override val startingLife: Int = 20
    }

    data object Modern : Format {
        override val name: String = "Modern"
        override val minPlayerCount: Int = 2
        override val maxPlayerCount: Int = 2
        override val startingLife: Int = 20
    }

    data object Commander : Format {
        override val name: String = "Commander"
        override val minPlayerCount: Int = 2
        override val maxPlayerCount: Int = 6
        override val startingLife: Int = 40
    }
}

fun formats(): List<Format> =
    listOf(
        Format.Commander,
        Format.Standard,
        Format.Modern,
    )