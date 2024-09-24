package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.ActiveEffect;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;

public class SkillCard extends AbstractCard {
    private static final CardType CARD_TYPE = CardType.SKILL;

    public SkillCard(Expansion e, String name, BonusStats bonusStats, Phase phaseToUse, String active, String passive) {
        super(e, name, CARD_TYPE, bonusStats, active, phaseToUse, passive);
    }

    public SkillCard(Expansion e, String name, BonusStats bonusStats, ActiveEffect active, String passive) {
        super(e, name, CARD_TYPE, bonusStats, active, passive);
    }
}
