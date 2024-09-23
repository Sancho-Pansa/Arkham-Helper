package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;

public final class UniqueItem extends AbstractItem {
    private static final CardType ITEM_TYPE = CardType.UNIQUE_ITEM;

    public UniqueItem(Expansion e,
                      String name,
                      int price,
                      String itemType,
                      byte hands,
                      BonusStats bonusStats,
                      String active,
                      Phase phaseToUse,
                      String passive
    ) {
        super(e, name, ITEM_TYPE, bonusStats, price, itemType, hands, active, phaseToUse, passive);
    }
}
