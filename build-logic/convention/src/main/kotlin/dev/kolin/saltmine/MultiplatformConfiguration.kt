package dev.kolin.saltmine

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun KotlinMultiplatformExtension.configureMultiplatformTargets() {
    applyDefaultHierarchyTemplate()

    extensions.configure(KotlinMultiplatformAndroidLibraryTarget::class.java) {
        it.compileSdk = project.libs.getVersionNumber("compileSdk")

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

    jvm()
}

internal fun KotlinMultiplatformExtension.configureOptionalMultiplatformTargets(
    extension: SaltMineExtension,
) {
    if (extension.iOSEnabled.get()) {
        listOf(
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "Shared"
                isStatic = true
            }
        }
    }

    if (extension.jsEnabled.get()) {
        js {
            browser()
        }
    }

    if (extension.wasmEnabled.get()) {
        @OptIn(ExperimentalWasmDsl::class)
        wasmJs {
            browser()
        }
    }

//    if (composeNativeEnabled) {
//        macosArm64()
//
//        iosArm64()
//        iosSimulatorArm64()
//    }
//    if (nativeEnabled) {
//        // tier 2
//        linuxX64()
//        linuxArm64()
//        watchosSimulatorArm64()
//        watchosX64()
//        watchosArm32()
//        watchosArm64()
//        tvosSimulatorArm64()
//        tvosX64()
//        tvosArm64()
//
//        // tier 3
//        // androidNativeArm32()
//        // androidNativeArm64()
//        // androidNativeX86()
//        // androidNativeX64()
//        mingwX64()
//        watchosDeviceArm64()
//    }
}