package io.sanchopansa.arkham.android

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.sanchopansa.arkham.core.investigators.Investigator

class InvestigatorViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _uiState = InvestigatorUiState.Loading
    var uiState: InvestigatorUiState by mutableStateOf<InvestigatorUiState>(InvestigatorUiState.Loading)
        private set

    fun loadInvestigator(investigator: Investigator) {
        uiState = InvestigatorUiState.Ready(investigator)
    }

    fun decrementHealth() = updateInvestigator { it.stamina.add(-1); it }
    fun incrementHealth() = updateInvestigator { it.stamina.add(1); it }

    private inline fun updateInvestigator(transform: (Investigator) -> Investigator) {
        val state = uiState as? InvestigatorUiState.Ready ?: return
        println(state.investigator.stamina.toString())
        uiState = state.copy(investigator = transform(state.investigator))
    }
}