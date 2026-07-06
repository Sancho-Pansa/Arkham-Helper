package io.sanchopansa.arkham.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.sanchopansa.arkham.android.ui.models.SkillUi

@Composable
fun SkillSlider(
    skill: SkillUi,
    onMoveLeft: () -> Unit,
    onMoveRight: () -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = skill.topLabel,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
            skill.topValues.forEachIndexed { index, value ->
                Text(
                    text = value.toString(),
                    modifier = Modifier.alpha(if (index == skill.currentIndex) 1f else 0.3f),
                    color = Color.Blue
                )
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = skill.bottomLabel,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
            skill.bottomValues.forEachIndexed { index, value ->
                Text(
                    text = value.toString(),
                    modifier = Modifier.alpha(if (index == skill.currentIndex) 1f else 0.3f),
                    color = Color.Red
                )
            }
        }

    }
}