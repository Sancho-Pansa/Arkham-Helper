package io.sanchopansa.arkham.cards;

import com.google.common.base.Enums;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class AbstractItem extends AbstractCard {
    private final int price;
    private final ItemType itemType;
    private final byte hands;
    private final ItemActive active;
    private final String passive;

    public AbstractItem(Expansion e,
                        String name,
                        CardType cardType,
                        int price,
                        String itemType,
                        byte hands,
                        String active,
                        Phase phaseToUse,
                        String passive
    ) {
        super(e, name, cardType, active);
        this.price = price;
        this.itemType = ItemType.from(itemType);
        this.hands = hands;
        this.active = new ItemActive(active, phaseToUse);
        this.passive = passive;
    }

    public AbstractItem(Expansion e,
                        String name,
                        CardType cardType,
                        int price,
                        ItemType itemType,
                        byte hands,
                        String active,
                        Phase phaseToUse,
                        String passive
    ) {
        super(e, name, cardType, active);
        this.price = price;
        this.itemType = itemType;
        this.hands = hands;
        this.active = new ItemActive(active, phaseToUse);
        this.passive = passive;
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

    public String getActive() {
        return active.description;
    }

    public Phase getPhaseToUse() {
        return this.active.usablePhase;
    }

    public String getPassive() {
        return passive;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("\n\tname", super.getName())
                .append("\n\texpansion", expansion)
                .append("\n\tcardType", super.getCardType())
                .append("\n\tprice", price)
                .append("\n\titemType", itemType)
                .append("\n\thands", hands)
                .append("\n\tactive", active)
                .append("\n\tpassive", passive)
                .append("\n")
                .toString();
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

    private record ItemActive(String description, Phase usablePhase) { };
}
