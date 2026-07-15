package io.sanchopansa.arkham.android.ui.models;

data class InvestigatorUi(
    val name: String,
    val title: String,
    val stamina: Int,
    val maxStamina: Int,
    val sanity: Int,
    val maxSanity: Int,
    val money: Int,
    val clueTokens: Int,
    val focus: Int,
    val maxFocus: Int,
    val abilityName: String,
    val abilityDescription: String,
    val commonItems: List<String>,
    val uniqueItems: List<String>,
    val spells: List<String>,
    val skillCards: List<String>,
    val speedSneak: SkillUi,
    val fightWill: SkillUi,
    val loreLuck: SkillUi
)

data class SkillUi(
    val topLabel: String,
    val bottomLabel: String,
    val topValues: List<Int>,
    val bottomValues: List<Int>,
    val currentIndex: Int
)