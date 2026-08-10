import dev.kolin.saltmine.SaltMineExtension
import dev.kolin.saltmine.configureJava
import dev.kolin.saltmine.configureKotlin
import dev.kolin.saltmine.getPluginId
import dev.kolin.saltmine.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

public class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val extension = SaltMineExtension.create(extensions)

        with(pluginManager) {
            apply(libs.getPluginId("kotlin"))
        }

        configureJava()

        afterEvaluate {
            configureKotlin(extension)
        }
    }
}
