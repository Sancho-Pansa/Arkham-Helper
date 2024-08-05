package io.sanchopansa.arkham.mechanics.cards;

import io.sanchopansa.arkham.mechanics.Expansion;
import io.sanchopansa.arkham.mechanics.Phase;

public abstract class AbstractItem extends AbstractCard {
    private final int price;
    private final ItemType itemType;
    private final Hands hands;
    private final Phase phaseToUse;

    public AbstractItem(Expansion e,
                        String name,
                        CardType cardType,
                        int price,
                        ItemType itemType,
                        Hands hands,
                        Phase phaseToUse,
                        String description
    ) {
        super(e, name, cardType, description);
        this.price = price;
        this.itemType = itemType;
        this.hands = hands;
        this.phaseToUse = phaseToUse;
    }

    public int getPrice() {
        return price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Hands getHands() {
        return hands;
    }

    public Phase getPhaseToUse() {
        return phaseToUse;
    }

    public enum Hands {
        NONE,
        ONE,
        BOTH
    }
    public enum ItemType {
        NONE,
        MAGICAL,
        PHYSICAL,
        TASK,
        MISSION,
        TOME
    }
}
