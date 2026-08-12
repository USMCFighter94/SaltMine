import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room3) apply false
}

subprojects {
    apply(plugin = rootProject.libs.plugins.ktlint.get().pluginId)

    configure<KtlintExtension> {
        version.set(rootProject.libs.versions.ktlint.asProvider())
        debug.set(true)
        verbose.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    dependencies {
        ktlintRuleset(rootProject.libs.ktlint.compose)
    }
}
