package dev.kolin.saltmine.core.domain

public sealed interface Format {
    public val name: String
    public val minPlayerCount: Int
    public val maxPlayerCount: Int
    public val startingLife: Int

    public data object Standard : Format {
        override val name: String = "Standard"
        override val minPlayerCount: Int = 2
        override val maxPlayerCount: Int = 2
        override val startingLife: Int = 20
    }

    public data object Modern : Format {
        override val name: String = "Modern"
        override val minPlayerCount: Int = 2
        override val maxPlayerCount: Int = 2
        override val startingLife: Int = 20
    }

    public data object Commander : Format {
        override val name: String = "Commander"
        override val minPlayerCount: Int = 2
        override val maxPlayerCount: Int = 6
        override val startingLife: Int = 40
    }
}

public fun formats(): List<Format> = listOf(
    Format.Commander,
    Format.Standard,
    Format.Modern,
)
