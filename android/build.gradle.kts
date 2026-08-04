plugins {
    alias(libs.plugins.saltmine.android)
}

android {
    namespace = "dev.kolin.saltmine"

    defaultConfig {
        applicationId = "dev.kolin.saltmine"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":shared:core:domain"))
}