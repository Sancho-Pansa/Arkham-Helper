package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.AbstractGameElement;
import io.sanchopansa.arkham.ActiveEffect;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;

import java.util.Objects;

public abstract class AbstractCard extends AbstractGameElement {
    protected final String name;
    protected final CardType cardType;
    protected final ActiveEffect active;
    protected final String passive;

    public AbstractCard(Expansion e, String name, CardType cardType) {
        super(e);
        this.name = name;
        this.cardType = cardType;
        this.active = null;
        this.passive = null;
    }

    public AbstractCard(Expansion e, String name, CardType cardType, ActiveEffect active, String passive) {
        super(e);
        this.name = name;
        this.cardType = cardType;
        this.active = active;
        this.passive = passive;
    }

    public AbstractCard(
            Expansion e,
            String name,
            CardType cardType,
            String active,
            Phase phaseToUse,
            String passive
    ) {
        super(e);
        this.name = name;
        this.cardType = cardType;
        this.active = new ActiveEffect(phaseToUse, active);
        this.passive = passive;

    }

    public String getName() {
        return name;
    }

    public CardType getCardType() {
        return cardType;
    }

    public ActiveEffect getActive() {
        return active;
    }

    public String getPassive() {
        return passive;
    }

    @Override
    public final boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof AbstractCard that)) return false;

        return name.equals(that.name) && cardType == that.cardType && this.expansion.equals(that.expansion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cardType);
    }

}
