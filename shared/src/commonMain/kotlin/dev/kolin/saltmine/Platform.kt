package dev.kolin.saltmine

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform