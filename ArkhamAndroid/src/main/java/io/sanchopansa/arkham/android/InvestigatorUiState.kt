package io.sanchopansa.arkham.android

import io.sanchopansa.arkham.core.investigators.Investigator

/*
    Несмотря на название, этот интерфейс описывает '''состояние экрана''', где есть Сыщик, а не
    состояние экземпляра Сыщика.
 */
sealed interface InvestigatorUiState {
    object Loading : InvestigatorUiState
    data class Ready(val investigator: Investigator) : InvestigatorUiState
}