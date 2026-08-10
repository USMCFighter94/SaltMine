package dev.kolin.saltmine.setup.ui

import dev.kolin.saltmine.core.domain.di.ScreenScope
import dev.zacsweers.metro.DependencyGraph

@DependencyGraph(scope = ScreenScope::class)
public interface SetupGraph {
    public val viewModel: SetupScreenViewModel
}