package dev.kolin.saltmine

import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.provider.Property

public interface SaltMineExtension {
    /**
     * Should warnings should be treated as errors?
     *
     * Defaults to true.
     */
    public val warningsAsErrors: Property<Boolean>

    /**
     * Should iOS targets be enabled?
     * 
     * Defaults to false.
     */
    public val iOSEnabled: Property<Boolean>

    /**
     * Should JavaScript targets be enabled?
     * 
     * Defaults to true.
     */
    public val jsEnabled: Property<Boolean>

    /**
     * Should WebAssembly targets be enabled?
     * 
     * Defaults to true.
     */
    public val wasmEnabled: Property<Boolean>

    public companion object {
        private const val NAME = "saltmine"

        public fun create(extensions: ExtensionContainer): SaltMineExtension =
            extensions.create(NAME, SaltMineExtension::class.java).apply {
                warningsAsErrors.convention(true)
                iOSEnabled.convention(false)
                jsEnabled.convention(true)
                wasmEnabled.convention(true)
            }
    }
}