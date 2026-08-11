plugins {
    alias(libs.plugins.saltmine.multiplatform)
    alias(libs.plugins.saltmine.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:domain"))
            implementation(project(":core:ui"))
            api(project(":database"))
            api(project(":network"))

            implementation(libs.coroutines.core)
        }
    }
}
