package io.sanchopansa.arkham.mechanics.cards;

import io.sanchopansa.arkham.mechanics.AbstractGameElement;
import io.sanchopansa.arkham.mechanics.Expansion;

public abstract class AbstractCard extends AbstractGameElement {
    private final String name;
    private final CardType cardType;
    private final String description;

    public AbstractCard(Expansion e, String name, CardType cardType, String description) {
        super(e);
        this.name = name;
        this.cardType = cardType;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getDescription() {
        return description;
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
