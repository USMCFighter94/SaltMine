package dev.kolin.saltmine

import dev.kolin.saltmine.core.domain.Commander
import dev.kolin.saltmine.setup.ScryfallDataSource
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Inject
internal class DesktopViewModel(
    private val dataSource: ScryfallDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val state: StateFlow<State>
        field = MutableStateFlow(State())

    fun fetch() {
        scope.launch {
            val commanders = withContext(dispatcher) { dataSource.fetchCommanders() }

            state.update {
                it.copy(commanders = commanders)
            }
        }
    }
}

internal data class State(
    val commanders: List<Commander> = emptyList(),
)
