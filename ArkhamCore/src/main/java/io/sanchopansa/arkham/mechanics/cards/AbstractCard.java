package io.sanchopansa.arkham.mechanics.cards;

import io.sanchopansa.arkham.mechanics.AbstractGameElement;
import io.sanchopansa.arkham.mechanics.Expansion;

public abstract class AbstractCard extends AbstractGameElement {
    private final String name;
    private final CardType type;

    public AbstractCard(Expansion e, String name, CardType type) {
        super(e);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public CardType getType() {
        return type;
    }

    public enum CardType {
        ALLY,
        BLIGHT,
        COMMON_ITEM,
        CORRUPTION,
        EXHIBIT_ITEM,
        INJURY,
        MADNESS,
        SKILL,
        SPECIAL,
        SPELL,
        UNIQUE_ITEM
    }
}
