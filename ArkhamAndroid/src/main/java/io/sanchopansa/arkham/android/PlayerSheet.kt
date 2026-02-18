package io.sanchopansa.arkham.android

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import io.sanchopansa.arkham.android.ui.PlayerSheetUiState
import io.sanchopansa.arkham.android.ui.PlayerSheetVm
import io.sanchopansa.arkham.android.ui.StatStepper
import io.sanchopansa.arkham.android.ui.UncappedStepper

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
                textAlign = TextAlign.Center
            )

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