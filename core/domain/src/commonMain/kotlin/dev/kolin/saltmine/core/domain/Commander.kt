package dev.kolin.saltmine.core.domain

data class Commander(
    val name: String,
    val types: List<String>,
    val identity: List<MTGColor>,
    val images: CommanderImage,
)