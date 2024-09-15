package io.sanchopansa.arkham.cards;

import com.google.common.base.Enums;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;

public abstract class AbstractItem extends AbstractCard {
    private final int price;
    private final ItemType itemType;
    private final byte hands;
    private final Phase phaseToUse;

    public AbstractItem(Expansion e,
                        String name,
                        CardType cardType,
                        int price,
                        String itemType,
                        byte hands,
                        Phase phaseToUse,
                        String description
    ) {
        super(e, name, cardType, description);
        this.price = price;
        this.itemType = ItemType.from(itemType);
        this.hands = hands;
        this.phaseToUse = phaseToUse;
    }

    public AbstractItem(Expansion e,
                        String name,
                        CardType cardType,
                        int price,
                        ItemType itemType,
                        byte hands,
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

    public byte getHands() {
        return hands;
    }

    public Phase getPhaseToUse() {
        return phaseToUse;
    }

    public enum ItemType {
        NONE,
        MAGICAL,
        PHYSICAL,
        TASK,
        MISSION,
        TOME;

        public static ItemType from(String value) {
            return Enums.getIfPresent(ItemType.class, value).or(ItemType.NONE);
        }
    }
}
