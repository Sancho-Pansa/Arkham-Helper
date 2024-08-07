package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.Expansion;

public class SkillCard extends AbstractCard {
    private static final AbstractCard.CardType CARD_TYPE = CardType.SKILL;

    public SkillCard(Expansion e, String name, CardType type, String description) {
        super(e, name, type, description);
    }
}
