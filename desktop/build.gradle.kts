import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.metro)
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":network"))
    implementation(project(":setup"))

    implementation(compose.desktop.currentOs)
    implementation(libs.coroutines.swing)

    implementation(libs.compose.uiToolingPreview)
}

compose.desktop {
    application {
        mainClass = "dev.kolin.saltmine.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "dev.kolin.saltmine"
            packageVersion = "1.0.0"
        }
    }
}