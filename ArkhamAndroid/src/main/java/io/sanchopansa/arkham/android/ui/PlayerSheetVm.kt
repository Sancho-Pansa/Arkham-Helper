package io.sanchopansa.arkham.android.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.sanchopansa.arkham.core.investigators.Investigator

class PlayerSheetVm(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var uiState by mutableStateOf<PlayerSheetUiState>(PlayerSheetUiState.Loading)
    private set

    private lateinit var investigator: Investigator

    fun loadInvestigator(investigator: Investigator) {
        uiState = PlayerSheetUiState.Ready(investigator.toUiModel())
        this.investigator = investigator
    }

    fun changeHealth(delta: Int) {
        this.investigator.stamina.add(delta)
        uiState = PlayerSheetUiState.Ready(investigator.toUiModel())
    }
}