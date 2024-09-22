package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.Expansion;

public final class Ally extends AbstractCard {
    private static final CardType CARD_TYPE = CardType.ALLY;

    public Ally(Expansion e, String name) {
        super(e, name, CARD_TYPE);
    }
}
