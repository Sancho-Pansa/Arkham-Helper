package io.sanchopansa.arkham.android

import androidx.compose.runtime.Composable
import io.sanchopansa.arkham.core.investigators.Investigator

@Composable
fun InvestigatorContent(
    investigator: Investigator,
    onHealthDecrease: () -> Unit,
    onHealthIncrease: () -> Unit
    ) {
    StatRow(investigator.stamina, onHealthDecrease, onHealthIncrease)
}