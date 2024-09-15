package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;

public class CommonItem extends AbstractItem {
    private static final AbstractCard.CardType ITEM_TYPE = CardType.COMMON_ITEM;

    public CommonItem(Expansion e,
                      String name,
                      int price,
                      String itemType,
                      byte hands,
                      Phase phaseToUse,
                      String description
    ) {
        super(e, name, ITEM_TYPE, price, itemType, hands, phaseToUse, description);
    }
}
