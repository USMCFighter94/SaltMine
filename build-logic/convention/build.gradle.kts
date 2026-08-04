import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    alias(libs.plugins.kotlin)
    `java-gradle-plugin`
    alias(libs.plugins.android.lint)
}

group = "dev.kolin.saltmine.buildlogic"

val javaVersion = libs.versions.java.get()

java {
    val version = JavaVersion.toVersion(javaVersion)
    sourceCompatibility = version
    targetCompatibility = version
}

kotlin {
    explicitApi()

    compilerOptions {
        allWarningsAsErrors = true
        apiVersion = KotlinVersion.KOTLIN_2_4
        languageVersion = apiVersion
        jvmTarget = JvmTarget.fromTarget(javaVersion)
        freeCompilerArgs.add("-Xjdk-release=$javaVersion")
    }
}

val functionalTest = sourceSets.create("functionalTest")
val functionalTestTask = tasks.register<Test>("functionalTest") {
    group = "verification"
    testClassesDirs = functionalTest.output.classesDirs
    classpath = functionalTest.runtimeClasspath
    useJUnitPlatform()
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }

    check {
        dependsOn(functionalTestTask)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    lintChecks(libs.androidx.lint)

    "functionalTestImplementation"(libs.kotlin.test)
    "functionalTestImplementation"(libs.kotlin.testJunit)
}

gradlePlugin {
    testSourceSets(functionalTest)

    plugins {
        register("saltmine-android-app") {
            id = libs.plugins.saltmine.android.get().pluginId
            implementationClass = "AndroidAppConventionPlugin"
        }

        register("saltmine-kotlin") {
            id = libs.plugins.saltmine.kotlin.get().pluginId
            implementationClass = "KotlinConventionPlugin"
        }

        register("saltmine-multiplatform") {
            id = libs.plugins.saltmine.multiplatform.get().pluginId
            implementationClass = "MultiplatformConventionPlugin"
        }

        register("saltmine-compose") {
            id = libs.plugins.saltmine.compose.get().pluginId
            implementationClass = "ComposeConventionPlugin"
        }

        register("saltmine-test") {
            id = libs.plugins.saltmine.test.get().pluginId
            implementationClass = "TestConventionPlugin"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}