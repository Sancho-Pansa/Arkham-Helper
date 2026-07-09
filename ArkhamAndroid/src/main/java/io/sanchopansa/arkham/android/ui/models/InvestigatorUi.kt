package io.sanchopansa.arkham.android.ui.models;

data class InvestigatorUi(
    val name: String,
    val stamina: Int,
    val maxStamina: Int,
    val sanity: Int,
    val maxSanity: Int,
    val money: Int,
    val clueTokens: Int,
    val commons: List<String>
)

data class SkillUi(
    val topLabel: String,
    val bottomLabel: String,
    val topValues: List<Int>,
    val bottomValues: List<Int>,
    val currentIndex: Int
)