package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.Expansion;

public class Ally extends AbstractCard {
    private static final AbstractCard.CardType CARD_TYPE = CardType.ALLY;

    public Ally(Expansion e, String name, String description) {
        super(e, name, CARD_TYPE, description);
    }
}
