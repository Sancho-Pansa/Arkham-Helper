package io.sanchopansa.arkham.android.ui

import io.sanchopansa.arkham.android.ui.models.InvestigatorUi
import io.sanchopansa.arkham.core.investigators.Investigator

fun Investigator.toUiModel(): InvestigatorUi {
    val itemNames = mutableListOf<String>()
    itemNames.addAll(commonItems.map { it.name })
    return InvestigatorUi(
        name,
        stamina.value,
        sanity.value,
        money,
        clueTokens,
        itemNames
    )
}