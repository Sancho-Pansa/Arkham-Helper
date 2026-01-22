package io.sanchopansa.arkham.android.ui.models;

data class InvestigatorUi(
    val name: String,
    val stamina: Int,
    val sanity: Int,
    val money: Int,
    val clueTokens: Int,
    val commons: List<String>
)
