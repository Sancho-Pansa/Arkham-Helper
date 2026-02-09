package io.sanchopansa.arkham.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import io.sanchopansa.arkham.android.ui.PlayerSheetUiState
import io.sanchopansa.arkham.android.ui.PlayerSheetVm

@Composable
fun PlayerSheet(modifier: Modifier, playerSheetVm: PlayerSheetVm) {
    val uiState = playerSheetVm.uiState;
    if (uiState is PlayerSheetUiState.Loading) {
        print("UI State is Loading")
    } else {
        val investigatorUi = playerSheetVm.getInvestigatorUi()
        Column(modifier) {
            Text(
                text = investigatorUi.name,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            Row {
                Button({ playerSheetVm.changeHealth(-1) }) {
                    Text("<")
                }
                Text(
                    text = "${investigatorUi.stamina} / ${investigatorUi.maxStamina}",
                    fontSize = 24.sp
                )
                Button({ playerSheetVm.changeHealth(1) }) {
                    Text(">")
                }
            }
            Row {
                Button({ println("Mind minus") }) {
                    Text("<")
                }
                Text(
                    text = "${investigatorUi.sanity} / ${investigatorUi.maxSanity}",
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
                    text = "$ ${investigatorUi.money}",
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
                    text = "${investigatorUi.clueTokens} \uD83D\uDD0E",
                    fontSize = 36.sp
                )
                Button({ println("Clues plus") }) {
                    Text(">")
                }
            }
        }
    }
}