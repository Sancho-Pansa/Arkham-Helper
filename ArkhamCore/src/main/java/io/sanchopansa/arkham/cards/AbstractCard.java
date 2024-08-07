package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.AbstractGameElement;
import io.sanchopansa.arkham.Expansion;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCard that)) return false;
        return Objects.equals(name, that.name) && cardType == that.cardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cardType);
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
