plugins {
    alias(libs.plugins.saltmine.multiplatform)
    alias(libs.plugins.saltmine.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.metro)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.coroutines.android)
        }
        commonMain.dependencies {
            implementation(libs.coroutines.core)
            implementation(libs.kotlin.serialization)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        wasmJsMain.dependencies {
            implementation(libs.wrappers.browser)
        }
    }
}
