import androidx.room3.gradle.RoomExtension
import dev.kolin.saltmine.getLibrary
import dev.kolin.saltmine.getPluginId
import dev.kolin.saltmine.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

public class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.getPluginId("room3"))
            apply(libs.getPluginId("ksp"))
        }

        with(extensions.getByType(KotlinMultiplatformExtension::class.java)) {
            with(sourceSets) {
                commonMain.dependencies {
                    implementation(libs.getLibrary("room3-runtime"))
                }

                androidMain.dependencies {
                    implementation(libs.getLibrary("sqlite-bundled"))
                }

                iosMain.dependencies {
                    implementation(libs.getLibrary("sqlite-bundled"))
                }

                named("desktopMain") {
                    it.dependencies {
                        implementation(libs.getLibrary("sqlite-bundled"))
                    }
                }

                wasmJsMain.dependencies {
                    implementation(libs.getLibrary("sqlite-web"))
                    implementation(
                        npm(
                            "sqlite-wasm-worker",
                            layout.projectDirectory.dir("worker").asFile,
                        )
                    )
                    implementation(libs.getLibrary("kotlin-browser"))
                    implementation(libs.getLibrary("coroutines-core-wasm-js"))
                }
            }
        }

        extensions.configure(RoomExtension::class.java) {
            it.schemaDirectory("$projectDir/schemas")
        }

        with(dependencies) {
            val roomCompiler = libs.getLibrary("room3-compiler")
            add("kspAndroid", roomCompiler)
            add("kspDesktop", roomCompiler)
            add("kspIosArm64", roomCompiler)
            add("kspIosSimulatorArm64", roomCompiler)
            add("kspWasmJs", roomCompiler)
        }
    }
}
