import dev.kolin.saltmine.SaltMineExtension
import dev.kolin.saltmine.configureJava
import dev.kolin.saltmine.configureKotlin
import dev.kolin.saltmine.configureMultiplatformTargets
import dev.kolin.saltmine.getLibrary
import dev.kolin.saltmine.getPluginId
import dev.kolin.saltmine.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

public class MultiplatformConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val extension = SaltMineExtension.create(extensions)

        with(pluginManager) {
            apply(libs.getPluginId("kotlin-multiplatform"))
            apply(libs.getPluginId("android-multiplatform"))
        }

        with(extensions.getByType(KotlinMultiplatformExtension::class.java)) {
            configureMultiplatformTargets()

            with(sourceSets) {
                commonTest.dependencies {
                    implementation(libs.getLibrary("kotlin-test"))
                    implementation(libs.getLibrary("coroutines-test"))
                }
            }
        }

        configureJava()

        afterEvaluate {
            configureKotlin(extension)
        }
    }
}
