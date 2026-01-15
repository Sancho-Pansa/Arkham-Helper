package io.sanchopansa.arkham.android

import android.graphics.Paint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import io.sanchopansa.arkham.core.investigators.Investigator

@Composable
fun InvestigatorComposable(player: Investigator) {
    Column {
        Text(
            text = player.name,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
        Row {
            Button({ println("Health minus") }) {
                Text("<")
            }
            Text(
                text = "${player.stamina.currentMaximum} / ${player.stamina.value}",
                fontSize = 36.sp
            )
            Button({ println("Health plus") }) {
                Text(">")
            }
        }
        Row {
            Button({ println("Mind minus") }) {
                Text("<")
            }
            Text(
                text = "${player.sanity.currentMaximum} / ${player.sanity.value}",
                fontSize = 36.sp
            )
            Button({ println("Mind plus") }) {
                Text(">")
            }
        }
        Row {
            Button({ println("Money minus") }) {
                Text("<")
            }
            Text(
                text = "$ ${player.money}",
                fontSize = 36.sp
            )
            Button({ println("Money plus") }) {
                Text(">")
            }
        }
        Row {
            Button({ println("Clues minus") }) {
                Text("<")
            }
            Text(
                text = "${player.clueTokens} \uD83D\uDD0E",
                fontSize = 36.sp
            )
            Button({ println("Clues plus") }) {
                Text(">")
            }
        }
    }

}