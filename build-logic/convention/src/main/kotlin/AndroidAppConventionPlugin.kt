import com.android.build.api.dsl.ApplicationExtension
import dev.kolin.saltmine.SaltMineExtension
import dev.kolin.saltmine.configure
import dev.kolin.saltmine.configureJava
import dev.kolin.saltmine.configureKotlin
import dev.kolin.saltmine.getLibrary
import dev.kolin.saltmine.getPluginId
import dev.kolin.saltmine.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

public class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val extension = SaltMineExtension.create(extensions)

        with(pluginManager) {
            apply(libs.getPluginId("android-app"))
            apply(libs.getPluginId("compose-compiler"))
        }

        extensions.configure(ApplicationExtension::class.java) {
            it.configure(target)
        }

        configureJava()

        with(dependencies) {
            add("implementation", libs.getLibrary("androidx-activity-compose"))
            add("implementation", libs.getLibrary("compose-uiToolingPreview"))
            add("debugImplementation", libs.getLibrary("compose-uiTooling"))
        }

        afterEvaluate {
            configureKotlin(extension)
        }
    }
}