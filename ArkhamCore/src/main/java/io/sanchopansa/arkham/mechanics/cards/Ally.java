package io.sanchopansa.arkham.mechanics.cards;

import io.sanchopansa.arkham.mechanics.Expansion;

public class Ally extends AbstractCard {
    private final String description;

    public Ally(Expansion e, String name, CardType type, String description) {
        super(e, name, type);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
