package dev.kolin.saltmine

import dev.kolin.saltmine.core.network.NetworkBindings
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph

@DependencyGraph(AppScope::class, bindingContainers = [NetworkBindings::class])
internal interface AppGraph {
    val viewModel: DesktopViewModel
}
