package dev.kolin.saltmine

import org.gradle.api.Project
import java.util.Properties

internal fun Project.readLocalProperties(): Properties? {
    val file = project.rootProject.file("local.properties")
    return if (file.exists()) {
        Properties().apply {
            file.inputStream().use { load(it) }
        }
    } else {
        null
    }
}

internal fun Project.readPropertyOrElse(
    key: String,
    default: String? = null,
    localProperties: Properties? = readLocalProperties(),
): String? =
    localProperties?.getProperty(key, null)
        ?: if (project.hasProperty(key)) project.property(key)?.toString() else default