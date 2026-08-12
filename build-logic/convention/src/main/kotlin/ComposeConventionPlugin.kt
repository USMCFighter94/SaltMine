import dev.kolin.saltmine.getLibrary
import dev.kolin.saltmine.getPluginId
import dev.kolin.saltmine.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

public class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.getPluginId("compose-multiplatform"))
            apply(libs.getPluginId("compose-compiler"))
        }

        with(extensions.getByType(KotlinMultiplatformExtension::class.java)) {
            with(sourceSets) {
                commonMain.dependencies {
                    implementation(libs.getLibrary("compose-runtime"))
                    implementation(libs.getLibrary("compose-foundation"))
                    implementation(libs.getLibrary("compose-material3"))
                    implementation(libs.getLibrary("compose-ui"))
                    implementation(libs.getLibrary("compose-components-resources"))
                    implementation(libs.getLibrary("compose-uiToolingPreview"))
                    implementation(libs.getLibrary("androidx-lifecycle-runtimeCompose"))
                }

                androidMain.dependencies {
                    implementation(libs.getLibrary("compose-uiToolingPreview"))
                    implementation(libs.getLibrary("compose-uiTooling"))
                }
            }
        }

        with(dependencies) {
            add("androidRuntimeClasspath", libs.getLibrary("compose-uiTooling"))
        }
    }
}
