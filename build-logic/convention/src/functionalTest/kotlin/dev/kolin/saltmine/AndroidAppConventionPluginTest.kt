package dev.kolin.saltmine

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.Test

internal class AndroidAppConventionPluginTest {

    @TempDir
    lateinit var projectDir: File

    private val libs by lazy { projectDir.resolve("libs.versions.toml") }
    private val settingsFile by lazy { projectDir.resolve("settings.gradle.kts") }
    private val parentBuildFile by lazy { projectDir.resolve("build.gradle.kts") }
    private val buildFile by lazy {
        projectDir.resolve("module").apply { mkdirs() }
            .resolve("build.gradle.kts")
    }

    @Test
    fun `applies activity compose dependency`() {
        libs.writeTestVersionCatalog()
        settingsFile.writeTestSettingsGradle()
        parentBuildFile.writeTestRootBuildFile()

        buildFile.writeText(
            """
            plugins {
                id("dev.kolin.saltmine.android.app")
            }
            
            android {
                namespace = "dev.kolin.saltmine"

                defaultConfig {
                    applicationId = "dev.kolin.saltmine"
                    versionCode = 1
                    versionName = "1.0"
                }
            }
            """.trimIndent()
        )

        GradleRunner.create()
            .withProjectDir(projectDir)
            .withPluginClasspath()
            .withArguments("tasks", "--stacktrace")
            .forwardOutput()
            .build()
    }
}