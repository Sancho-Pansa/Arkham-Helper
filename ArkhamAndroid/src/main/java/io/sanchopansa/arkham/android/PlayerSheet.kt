package io.sanchopansa.arkham.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.sanchopansa.arkham.core.investigators.Investigator
import io.sanchopansa.arkham.factories.JsonGameFactory

@Composable
fun PlayerSheet(modifier: Modifier, player: Investigator) {
    val investigatorState = remember { mutableStateOf(player) }
    val character = investigatorState.value
    Column(modifier) {
        Text(
            text = investigatorState.component1().name,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
        Row {
            Button({ character.stamina.add(-1) }) {
                Text("<")
            }
            Text(
                text = "${character.stamina.currentMaximum} / ${character.stamina.value}",
                fontSize = 24.sp
            )
            Button({ character.stamina.add(1);println("A") }) {
                Text(">")
            }
        }
        Row {
            Button({ println("Mind minus") }) {
                Text("<")
            }
            Text(
                text = "${character.sanity.currentMaximum} / ${character.sanity.value}",
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
                text = "$ ${character.money}",
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

@Preview(showBackground = true)
@Composable
fun CharPreview() {
    val context = LocalContext.current
    val jsonSource = AndroidJsonSource(context)
    val gameVault = JsonGameFactory(jsonSource).createVault()
    val player = gameVault.investigators.toList().singleOrNull { investigator -> investigator.name == "Аманда Шарп" }

    MaterialTheme {
        PlayerSheet(modifier = Modifier, player = player!!)
    }
}