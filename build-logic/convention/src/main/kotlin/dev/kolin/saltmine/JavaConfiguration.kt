package dev.kolin.saltmine

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion

internal fun Project.configureJava() {
    extensions.configure(JavaPluginExtension::class.java) { extension ->
        extension.toolchain {
            it.languageVersion.set(
                JavaLanguageVersion.of(
                    libs.getVersionNumber("java")
                )
            )
        }
    }
}
