package dev.kolin.saltmine

import dev.kolin.saltmine.core.network.NetworkBindings
import dev.kolin.saltmine.setup.ui.SetupGraph
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph

@DependencyGraph(
    scope = AppScope::class,
    bindingContainers = [
        NetworkBindings::class,
    ],
)
internal interface DesktopGraph {
    val viewModel: DesktopViewModel
    val setupGraph: SetupGraph
}
