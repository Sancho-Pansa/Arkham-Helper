package io.sanchopansa.arkham.core.cards;

import com.google.common.base.Enums;
import io.sanchopansa.arkham.core.ActiveEffect;
import io.sanchopansa.arkham.core.Expansion;
import io.sanchopansa.arkham.core.Phase;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class AbstractItem extends AbstractCard {
    protected final int price;
    protected final ItemType itemType;
    protected final int hands;

    public AbstractItem(Expansion e,
                        String name,
                        CardType cardType,
                        BonusStats bonusStats,
                        int price,
                        String itemType,
                        int hands,
                        String active,
                        Phase phaseToUse,
                        String passive
    ) {
        super(e, name, cardType, bonusStats, active, phaseToUse, passive);
        this.price = price;
        this.itemType = ItemType.from(itemType);
        this.hands = hands;
    }

    public AbstractItem(Expansion e,
                        String name,
                        CardType cardType,
                        BonusStats bonusStats,
                        int price,
                        ItemType itemType,
                        int hands,
                        String active,
                        Phase phaseToUse,
                        String passive
    ) {
        super(e, name, cardType, bonusStats, active, phaseToUse, passive);
        this.price = price;
        this.itemType = itemType;
        this.hands = hands;
    }

    public AbstractItem(Expansion e,
                        String name,
                        CardType cardType,
                        BonusStats bonusStats,
                        int price,
                        ItemType itemType,
                        byte hands,
                        ActiveEffect active,
                        String passive
    ) {
        super(e, name, cardType, bonusStats, active, passive);
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

    public int getHands() {
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
                .append("\n\tstats", bonusStats)
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
}
