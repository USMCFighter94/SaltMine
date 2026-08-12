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

    public companion object {
        private const val NAME = "saltmine"

        public fun create(extensions: ExtensionContainer): SaltMineExtension =
            extensions.create(NAME, SaltMineExtension::class.java).apply {
                warningsAsErrors.convention(true)
            }
    }
}