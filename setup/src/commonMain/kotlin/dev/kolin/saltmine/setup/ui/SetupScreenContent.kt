package dev.kolin.saltmine.setup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kolin.saltmine.core.domain.Format
import dev.kolin.saltmine.core.domain.formats

@Composable
fun SetupScreenContent(
    gameName: String,
    formats: List<Format>,
    selectedFormat: Format,
    players: Int,
    onGameNameChanged: (String) -> Unit,
    onFormatChanged: (Format) -> Unit,
    onPlayerCountChanged: (Int) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = gameName,
            onValueChange = onGameNameChanged,
            label = { Text("Game Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Format",
            color = Color.White,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            formats.forEach { format ->
                TextButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Black,
                        containerColor = if (format == selectedFormat) Color.Red else Color.White,
                    ),
                    onClick = { onFormatChanged(format) },
                ) {
                    Text(
                        text = format.name,
                        color = if (format == selectedFormat) Color.White else Color.Black,
                    )
                }
            }
        }

        Text(
            text = "Player Count",
            color = Color.White,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextButton(
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black,
                ),
                enabled = players > selectedFormat.minPlayerCount,
                onClick = { onPlayerCountChanged(players - 1) }
            ) {
                Text(
                    text = "-",
                    fontSize = 100.sp,
                )
            }

            Text(
                text = players.toString(),
                fontSize = 100.sp,
                color = Color.White,
            )

            TextButton(
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black,
                ),
                enabled = players < selectedFormat.maxPlayerCount,
                onClick = { onPlayerCountChanged(players + 1) }
            ) {
                Text(
                    text = "+",
                    fontSize = 64.sp,
                )
            }
        }

        TextButton(
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.Black,
                containerColor = Color.White,
            ),
            onClick = onSave,
        ) {
            Text(
                text = "Save",
            )
        }
    }
}

@Composable
@Preview
private fun SetupScreenContentPreview() {
    var gameName by remember { mutableStateOf("") }
    var format by remember { mutableStateOf<Format>(Format.Commander) }
    var playerCount by remember { mutableStateOf(format.minPlayerCount) }

    SetupScreenContent(
        gameName = gameName,
        formats = formats(),
        selectedFormat = format,
        players = playerCount,
        onGameNameChanged = { gameName = it },
        onFormatChanged = { format = it },
        onPlayerCountChanged = { playerCount = it },
        onSave = {},
    )
}