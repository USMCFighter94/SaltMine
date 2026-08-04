package dev.kolin.saltmine

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

internal class MultiplatformConventionPluginTest {

    @TempDir
    lateinit var projectDir: File

    private val settingsFile by lazy { projectDir.resolve("settings.gradle.kts") }
    private val buildFile by lazy { projectDir.resolve("build.gradle.kts") }

    @Test
    fun `can run greeting task`() {
        settingsFile.writeText("")
        buildFile.writeText(
            """
            plugins {
                id("dev.kolin.saltmine.multiplatform")
            }
            
            kotlin {
                android {
                    namespace = "dev.kolin.saltmine.test"
                }
            }
            """.trimIndent()
        )

        val result = GradleRunner.create()
            .withProjectDir(projectDir)
            .withPluginClasspath()
            .forwardOutput()
            .build()

//        assertTrue(result.task(":greet")?.outcome == TaskOutcome.SUCCESS)
    }
}