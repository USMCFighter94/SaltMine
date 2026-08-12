plugins {
    alias(libs.plugins.saltmine.multiplatform)
    alias(libs.plugins.saltmine.room)
    alias(libs.plugins.metro)
}

kotlin {
    compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:domain"))
        }
    }
}
