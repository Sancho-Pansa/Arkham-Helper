package io.sanchopansa.arkham.mechanics.cards;

import io.sanchopansa.arkham.mechanics.Expansion;

public class SkillCard extends AbstractCard {
    private static final AbstractCard.CardType CARD_TYPE = CardType.SKILL;

    public SkillCard(Expansion e, String name, CardType type, String description) {
        super(e, name, type, description);
    }
}
