package dev.kolin.saltmine

import java.io.File

internal fun File.writeTestRootBuildFile() {
    writeText(
        """
        plugins {
            alias(libs.plugins.android.app) apply false
            alias(libs.plugins.android.multiplatform) apply false
            alias(libs.plugins.compose.multiplatform) apply false
            alias(libs.plugins.compose.compiler) apply false
            alias(libs.plugins.kotlin) apply false
            alias(libs.plugins.kotlin.multiplatform) apply false
        }
        """.trimIndent()
    )
}