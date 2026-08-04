package dev.kolin.saltmine

import java.io.File

internal fun File.writeTestSettingsGradle() {
    writeText(
        """
            pluginManagement {
                repositories {
                    gradlePluginPortal()
                    google()
                }
            }

            dependencyResolutionManagement {
                @Suppress("UnstableApiUsage")
                repositories {
                    google()
                    mavenCentral()
                }
                
                versionCatalogs {
                    create("libs") {
                        from(files("libs.versions.toml"))
                    }
                }
            }
        """.trimIndent()
    )
}