package io.sanchopansa.arkham.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.twotone.AccountBox
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.sanchopansa.arkham.core.Expansion
import io.sanchopansa.arkham.core.Phase
import io.sanchopansa.arkham.core.investigators.Investigator

@Composable
fun PlayerSheet(modifier: Modifier, playerSheetVm: PlayerSheetVm) {
    val uiState = playerSheetVm.uiState
    if (uiState is PlayerSheetUiState.Loading) {
        print("UI State is Loading")
    } else {
        val investigatorUi = playerSheetVm.getInvestigatorUi()
        Column(modifier) {
            Text(
                text = investigatorUi.name,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Image(
                    imageVector = Icons.TwoTone.AccountBox,
                    contentDescription = "Icon",
                    Modifier.size(120.dp)
                )
                Column(horizontalAlignment = Alignment.End) {
                    StatStepper(
                        modifier = modifier,
                        value = investigatorUi.stamina,
                        max = investigatorUi.maxStamina,
                        minusAction = { playerSheetVm.changeHealth(-1) },
                        plusAction = { playerSheetVm.changeHealth(1) }
                    )

                    StatStepper(
                        modifier = modifier,
                        value = investigatorUi.sanity,
                        max = investigatorUi.maxSanity,
                        minusAction = { playerSheetVm.changeSanity(-1) },
                        plusAction = { playerSheetVm.changeSanity(1) }
                    )

                    UncappedStepper(
                        modifier = modifier,
                        value = investigatorUi.money,
                        prependText = "$",
                        minusAction = { playerSheetVm.changeMoney(-1) },
                        plusAction = { playerSheetVm.changeMoney(1) }
                    )

                    UncappedStepper(
                        modifier = modifier,
                        value = investigatorUi.clueTokens,
                        appendIcon = Icons.TwoTone.Search,
                        minusAction = { playerSheetVm.changeClueTokens(-1) },
                        plusAction = { playerSheetVm.changeClueTokens(1) }
                    )
                }
            }
        }
    }
}


@Preview(
    showBackground = true, showSystemUi = false,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Composable
fun PlayerSheetPreview() {
    val playerVm = PlayerSheetVm()
    playerVm.loadInvestigator(
        Investigator(
            Expansion.VANILLA,
            "Аманда Шарп",
            "Студентка",
            5,
            5,
            3,
            0,
            4,
            0,
            6,
            2,
            6,
            "Прилежная ученица",
            "Описание",
            Phase.ANY
        )
    )
    PlayerSheet(
        modifier = Modifier,
        playerSheetVm = playerVm
    )
}
