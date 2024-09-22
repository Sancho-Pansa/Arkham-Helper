package io.sanchopansa.arkham.cards;

import com.google.common.base.Enums;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class AbstractItem extends AbstractCard {
    private final int price;
    private final ItemType itemType;
    private final byte hands;

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
        super(e, name, cardType, active, phaseToUse, passive);
        this.price = price;
        this.itemType = ItemType.from(itemType);
        this.hands = hands;
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
        super(e, name, cardType, active, phaseToUse, passive);
        this.price = price;
        this.itemType = itemType;
        this.hands = hands;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("\n\tname", name)
                .append("\n\texpansion", expansion)
                .append("\n\tcardType", cardType)
                .append("\n\tprice", price)
                .append("\n\titemType", itemType)
                .append("\n\thands", hands)
                .append("\n\tactive", active)
                .append("\n\tactive", passive)
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
}
