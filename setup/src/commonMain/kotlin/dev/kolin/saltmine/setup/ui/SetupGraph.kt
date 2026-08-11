package dev.kolin.saltmine.setup.ui

import dev.kolin.saltmine.db.DatabaseBindings
import dev.kolin.saltmine.core.domain.di.ScreenScope
import dev.zacsweers.metro.GraphExtension

@GraphExtension(
    scope = ScreenScope::class,
    bindingContainers = [
        DatabaseBindings::class,
    ],
)
public interface SetupGraph {
    public val viewModel: SetupScreenViewModel
}
