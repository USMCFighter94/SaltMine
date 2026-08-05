package dev.kolin.saltmine.setup.ui

import dev.kolin.saltmine.core.domain.Format
import dev.kolin.saltmine.core.domain.di.ScreenScope
import dev.kolin.saltmine.core.domain.formats
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@SingleIn(scope = ScreenScope::class)
@Inject
class SetupScreenViewModel(
    startingState: State = State(),
) {

    val state: StateFlow<State>
        field = MutableStateFlow(startingState)

    fun onGameNameChanged(gameName: String) {
        state.update { it.copy(gameName = gameName) }
    }

    fun onFormatChanged(format: Format) {
        state.update { state ->
            state.copy(
                selectedFormat = format,
                players = state.players.takeIf { it in format.minPlayerCount..format.maxPlayerCount }
                    ?: format.minPlayerCount
            )
        }
    }

    fun onPlayerCountChanged(newCount: Int) {
        state.update {
            it.copy(
                players = newCount.coerceIn(
                    it.selectedFormat.minPlayerCount,
                    it.selectedFormat.maxPlayerCount,
                )
            )
        }
    }

    fun save() {
        println("You've saved!")
    }
}

data class State(
    val gameName: String = "",
    val formats: List<Format> = formats(),
    val selectedFormat: Format = Format.Commander,
    val players: Int = selectedFormat.minPlayerCount,
) {
    val startingLife: Int
        get() = selectedFormat.startingLife
}