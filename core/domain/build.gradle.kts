plugins {
    alias(libs.plugins.saltmine.multiplatform)
}

kotlin {
    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
