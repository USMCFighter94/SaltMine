package dev.kolin.saltmine.setup.ui

import dev.kolin.saltmine.core.domain.Format
import dev.kolin.saltmine.core.domain.formats
import dev.kolin.saltmine.db.game.GameDatabaseAccessor
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SingleIn(scope = AppScope::class)
@Inject
public class SetupScreenViewModel(
    startingState: State = State(),
    private val gameDatabaseAccessor: GameDatabaseAccessor,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    public val state: StateFlow<State>
        field = MutableStateFlow(startingState)

    public fun onGameNameChanged(gameName: String) {
        state.update { it.copy(gameName = gameName) }
    }

    public fun onFormatChanged(format: Format) {
        state.update { state ->
            state.copy(
                selectedFormat = format,
                players = state.players.takeIf { it in format.minPlayerCount..format.maxPlayerCount }
                    ?: format.minPlayerCount,
            )
        }
    }

    public fun onPlayerCountChanged(newCount: Int) {
        state.update {
            it.copy(
                players = newCount.coerceIn(
                    it.selectedFormat.minPlayerCount,
                    it.selectedFormat.maxPlayerCount,
                ),
            )
        }
    }

    public fun save() {
        scope.launch {
            val rowId = withContext(Dispatchers.Default) {
                gameDatabaseAccessor.createNewGame(
                    name = state.value.gameName,
                    players = state.value.players,
                    format = state.value.selectedFormat,
                )
            }

            println("You've created a new game! $rowId!")
        }
    }
}

public data class State(
    val gameName: String = "",
    val formats: List<Format> = formats(),
    val selectedFormat: Format = Format.Commander,
    val players: Int = selectedFormat.minPlayerCount,
) {
    val startingLife: Int
        get() = selectedFormat.startingLife
}
