package io.sanchopansa.arkham.android.ui

import io.sanchopansa.arkham.android.ui.models.InvestigatorUi
import io.sanchopansa.arkham.android.ui.models.SkillUi
import io.sanchopansa.arkham.core.investigators.Investigator
import io.sanchopansa.arkham.core.investigators.Skill

fun Investigator.toUiModel(): InvestigatorUi {
    val itemNames = mutableListOf<String>()
    itemNames.addAll(commonItems.map { it.name })
    return InvestigatorUi(
        name,
        stamina.value,
        stamina.currentMaximum,
        sanity.value,
        sanity.currentMaximum,
        money,
        clueTokens,
        itemNames
    )
}

fun Skill.toUiModel(topLabel: String, bottomLabel: String): SkillUi {
    return SkillUi(
        topLabel = topLabel,
        bottomLabel = bottomLabel,
        topValues = (0..3).map { leftBlue + it },
        bottomValues = (0..3).map {rightRed - it },
        currentIndex = sliderIndex
    )
}