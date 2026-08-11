package dev.kolin.saltmine

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

internal fun Project.configureKotlin(
    extension: SaltMineExtension,
) {
    extensions.configure(KotlinProjectExtension::class.java) { it.explicitApi() }

    tasks.withType(KotlinCompilationTask::class.java).configureEach {
        it.compilerOptions {
            allWarningsAsErrors.set(extension.warningsAsErrors)

            if (this is KotlinJvmCompilerOptions) {
                jvmTarget.set(JvmTarget.fromTarget(libs.getVersionString("java")))
            }

            val kotlinVersion = libs.getVersionString("kotlin")
                .split(".")
                .let { (major, minor) -> KotlinVersion.fromVersion("$major.$minor") }

            languageVersion.set(kotlinVersion)
            apiVersion.set(kotlinVersion)
        }
    }
}