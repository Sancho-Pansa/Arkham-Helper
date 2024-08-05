package io.sanchopansa.arkham.mechanics.cards;

import io.sanchopansa.arkham.mechanics.Expansion;

public class Ally extends AbstractCard {
    private static final AbstractCard.CardType CARD_TYPE = CardType.ALLY;

    public Ally(Expansion e, String name, String description) {
        super(e, name, CARD_TYPE, description);
    }
}
