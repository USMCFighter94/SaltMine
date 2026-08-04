package dev.kolin.saltmine

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension

internal val Project.libs: VersionCatalog
    get() = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

internal fun VersionCatalog.getVersionNumber(name: String): Int =
    findVersion(name).get().requiredVersion.toInt()

internal fun VersionCatalog.getVersionString(name: String): String =
    findVersion(name).get().requiredVersion

internal fun VersionCatalog.getPluginId(name: String): String =
    findPlugin(name).get().get().pluginId

internal fun VersionCatalog.getLibrary(name: String): MinimalExternalModuleDependency =
    findLibrary(name).get().get()