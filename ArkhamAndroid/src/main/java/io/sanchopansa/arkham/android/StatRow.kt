package io.sanchopansa.arkham.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.sanchopansa.arkham.core.investigators.Stat

@Composable
fun StatRow(
    stat: Stat,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row {
        Text(
            text = "-",
            modifier = Modifier.clickable { onDecrease() }
        )
        Text("${stat.value} / ${stat.currentMaximum}")
        Text(
            text = "+",
            modifier = Modifier.clickable { onIncrease() }
        )
    }
}