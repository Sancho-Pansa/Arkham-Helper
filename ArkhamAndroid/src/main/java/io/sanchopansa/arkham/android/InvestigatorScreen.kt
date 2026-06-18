package io.sanchopansa.arkham.android

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InvestigatorScreen(modifier: Modifier = Modifier, viewModel: InvestigatorViewModel = viewModel()) {
    when (val state = viewModel.uiState) {
        is InvestigatorUiState.Loading -> {
            return
        }
        is InvestigatorUiState.Ready -> {
            Column(modifier) {
                Text(
                    text = state.investigator.name,
                    fontSize = 32.sp
                )
                InvestigatorContent(
                    investigator = state.investigator,
                    onHealthDecrease = viewModel::decrementHealth,
                    onHealthIncrease = viewModel::incrementHealth
                )
            }
        }
    }
}