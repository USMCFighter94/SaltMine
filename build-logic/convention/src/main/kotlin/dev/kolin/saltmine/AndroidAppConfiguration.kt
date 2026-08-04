package dev.kolin.saltmine

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project

internal fun ApplicationExtension.configure(
    project: Project,
) {
    compileSdk = project.libs.getVersionNumber("compileSdk")
    buildFeatures.compose = true

    defaultConfig {
        minSdk = project.libs.getVersionNumber("minSdk")
        targetSdk = project.libs.getVersionNumber("targetSdk")
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            applicationIdSuffix = ".debug"
            isDefault = true
            isDebuggable = true
        }
    }
}