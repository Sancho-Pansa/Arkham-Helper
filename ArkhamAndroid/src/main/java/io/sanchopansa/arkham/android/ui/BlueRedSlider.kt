package io.sanchopansa.arkham.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val ROW_LENGTH: Int = 4

@Preview
@Composable
fun BlueRedSlider(
    modifier: Modifier = Modifier,
    minBlue: Int = 0,
    blueLabel: String = "Blue",
    maxRed: Int = 4,
    redLabel: String = "Red",
    initialFocus: Int = 2,
    initialIndex: Int = 0
) {
    val blueValues = remember(minBlue) { (minBlue..minBlue + ROW_LENGTH).toList() }
    val redValues = remember(maxRed) { (maxRed - ROW_LENGTH..maxRed).toList() }

    var focus by remember { mutableIntStateOf(initialFocus) }
    var index by remember { mutableIntStateOf(initialIndex) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)

    ) {
        val rowModifier = Modifier.fillMaxWidth()
        val rowArrangement = Arrangement.SpaceEvenly
        val rowValignment = Alignment.CenterVertically

        // TODO: Grid layout!
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = rowValignment
        ) {
            Text(
            text = blueLabel,
            style = MaterialTheme.typography.labelLarge
        )
            Row(
                modifier = rowModifier,
                horizontalArrangement = rowArrangement,
                verticalAlignment = rowValignment
            )
            {

                blueValues.forEachIndexed { index, value ->
                    SliderCell(
                        value = value.toString(),
                        isSelected = index == initialIndex,
                        color = Color.Blue.copy(alpha = 0.8f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = rowModifier,
            verticalAlignment = rowValignment
        ) {
            Text(
                text = redLabel,
                style = MaterialTheme.typography.labelLarge
            )
            Row(
                modifier = rowModifier,
                horizontalArrangement = rowArrangement,
                verticalAlignment = rowValignment
            ) {

                redValues.forEachIndexed { index, value ->
                    SliderCell(
                        value = value.toString(),
                        isSelected = index == initialIndex,
                        color = Color.Red.copy(alpha = 0.8f)
                    )
                }
            }
        }

    }
}

@Composable
fun SliderCell(
    value: String = "",
    isSelected: Boolean = false,
    color: Color
) {
    val boxModifier = Modifier
        .padding(4.dp)
        .border(
            1.dp,
            MaterialTheme.colorScheme.outline,
            RoundedCornerShape(4.dp)
        )
        .background(
            color = if (isSelected) color.copy(alpha = 0.3f) else Color.Transparent,
            shape = RoundedCornerShape(4.dp)
        )
    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp
        )
    }
}