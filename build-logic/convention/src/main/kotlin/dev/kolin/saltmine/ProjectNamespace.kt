package dev.kolin.saltmine

import org.gradle.api.Project

private const val BASE_PACKAGE = "dev.kolin.saltmine"

internal fun Project.namespace(): String {
    val suffix = path.removePrefix(":")
        .split(":")
        .joinToString(".") { segment ->
            segment.replace(Regex("[^A-Za-z0-9_]"), "_")
                .lowercase()
        }
    return "$BASE_PACKAGE.$suffix"
}