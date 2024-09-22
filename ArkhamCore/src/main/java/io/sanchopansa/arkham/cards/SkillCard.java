package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.Expansion;

public class SkillCard extends AbstractCard {
    private static final CardType CARD_TYPE = CardType.SKILL;

    public SkillCard(Expansion e, String name, String description) {
        super(e, name, CARD_TYPE);
    }
}
