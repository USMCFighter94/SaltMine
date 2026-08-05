package dev.kolin.saltmine.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
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
import dev.kolin.saltmine.core.ui.icons.Icon
import org.jetbrains.compose.resources.stringResource
import saltmine.core.ui.generated.resources.Res
import saltmine.core.ui.generated.resources.icon_add_content_description
import saltmine.core.ui.generated.resources.icon_subtract_content_description

@Composable
public fun ValueChangeComponent(
    value: Int,
    onValueChanged: (Int) -> Unit,
    minValue: Int = Int.MIN_VALUE,
    maxValue: Int = Int.MAX_VALUE,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        IconButton(
            colors = colors(),
            enabled = value > minValue,
            onClick = { onValueChanged(value - 1) }
        ) {
            Icon(
                imageVector = Icon.subtract,
                contentDescription = stringResource(Res.string.icon_subtract_content_description),
                modifier = Modifier.size(100.dp),
            )
        }

        Text(
            text = value.toString(),
            fontSize = 100.sp,
            color = Color.White,
        )

        IconButton(
            colors = colors(),
            enabled = value < maxValue,
            onClick = { onValueChanged(value + 1) }
        ) {
            Icon(
                imageVector = Icon.add,
                contentDescription = stringResource(Res.string.icon_add_content_description),
                modifier = Modifier.size(100.dp),
            )
        }
    }
}

@Composable
private fun colors(): IconButtonColors =
    IconButtonDefaults.iconButtonColors(
        contentColor = Color.White,
        containerColor = Color.Transparent,
    )

@Composable
@Preview
private fun SetupScreenContentPreview() {
    var playerCount by remember { mutableStateOf(2) }

    ValueChangeComponent(
        value = playerCount,
        minValue = 2,
        maxValue = 6,
        onValueChanged = { playerCount = it },
    )
}