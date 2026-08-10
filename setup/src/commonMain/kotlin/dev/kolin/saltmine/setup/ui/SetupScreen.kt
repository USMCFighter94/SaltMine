package dev.kolin.saltmine.setup.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
public fun SetupScreen(
    viewModel: SetupScreenViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SetupScreenContent(
        gameName = state.gameName,
        formats = state.formats,
        selectedFormat = state.selectedFormat,
        players = state.players,
        onGameNameChange = viewModel::onGameNameChanged,
        onFormatChange = viewModel::onFormatChanged,
        onPlayerCountChange = viewModel::onPlayerCountChanged,
        onSave = viewModel::save,
        modifier = modifier,
    )
}
