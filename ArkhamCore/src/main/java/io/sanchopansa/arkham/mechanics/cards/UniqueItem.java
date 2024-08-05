package io.sanchopansa.arkham.mechanics.cards;

import io.sanchopansa.arkham.mechanics.Expansion;
import io.sanchopansa.arkham.mechanics.Phase;

public class UniqueItem extends AbstractItem {
    private static final AbstractCard.CardType ITEM_TYPE = CardType.UNIQUE_ITEM;

    public UniqueItem(Expansion e,
                      String name,
                      int price,
                      ItemType itemType,
                      Hands hands,
                      Phase phaseToUse,
                      String description
    ) {
        super(e, name, ITEM_TYPE, price, itemType, hands, phaseToUse, description);
    }
}
