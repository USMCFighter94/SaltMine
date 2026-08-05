package dev.kolin.saltmine

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun KotlinMultiplatformExtension.configureMultiplatformTargets() {
    applyDefaultHierarchyTemplate()

    extensions.configure(KotlinMultiplatformAndroidLibraryTarget::class.java) {
        it.compileSdk = project.libs.getVersionNumber("compileSdk")
        it.namespace = project.namespace()

        it.compilerOptions.jvmTarget.set(JvmTarget.fromTarget(project.libs.getVersionString("java")))
        it.androidResources.enable = true

        it.withHostTest {
            isIncludeAndroidResources = true
        }

        it.withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(project.libs.getVersionString("java")))
        }
    }
}

internal fun KotlinMultiplatformExtension.configureOptionalMultiplatformTargets(
    extension: SaltMineExtension,
) {
    if (extension.iOSEnabled.get()) {
        iosArm64()
        iosSimulatorArm64()
    }

    if (extension.wasmEnabled.get()) {
        @OptIn(ExperimentalWasmDsl::class)
        wasmJs {
            browser()
        }
    }

//    if (composeNativeEnabled) {
//        macosArm64()
//    }
}