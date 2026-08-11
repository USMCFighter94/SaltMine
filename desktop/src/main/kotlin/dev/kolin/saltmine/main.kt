@file:Suppress("ktlint:standard:filename")

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kolin.saltmine.setup.ui.SetupScreen
import dev.zacsweers.metro.createGraph

public fun main(): Unit = application {
    val graph = createGraph<DesktopGraph>()
    val viewModel = graph.setupGraph.viewModel

    Window(
        onCloseRequest = ::exitApplication,
        title = "SaltMine",
    ) {
        SetupScreen(viewModel)
//        App(viewModel)
    }
}

@Composable
private fun App(
    viewModel: DesktopViewModel,
    modifier: Modifier = Modifier,
) {
    MaterialTheme {
        val state by viewModel.state.collectAsStateWithLifecycle()

        Column(
            modifier = modifier
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
