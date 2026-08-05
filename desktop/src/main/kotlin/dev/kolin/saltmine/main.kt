package dev.kolin.saltmine

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.zacsweers.metro.createGraph

fun main() = application {
    val graph = createGraph<AppGraph>()
    val viewModel = graph.viewModel

    Window(
        onCloseRequest = ::exitApplication,
        title = "SaltMine",
    ) {
        App(viewModel)
    }
}

@Composable
@Preview
private fun App(
    viewModel: DesktopViewModel,
) {
    MaterialTheme {
        val state by viewModel.state.collectAsStateWithLifecycle()

        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { viewModel.fetch() }) {
                Text("Load")
            }

            AnimatedVisibility(state.commanders.isNotEmpty()) {
                LazyColumn(contentPadding = PaddingValues(8.dp)) {
                    items(state.commanders) { commander ->
                        Text(commander.name)
                    }
                }
            }
        }
    }
}