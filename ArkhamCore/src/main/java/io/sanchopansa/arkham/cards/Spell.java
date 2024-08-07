package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;

public class Spell extends AbstractItem {
    private static final AbstractCard.CardType CARD_TYPE = CardType.SPELL;
    private static final ItemType ITEM_TYPE = ItemType.NONE;
    private static final int PRICE = 0;

    private final int castingMod;
    private final int sanityCost;

    public Spell(Expansion e,
                 String name,
                 Hands hands,
                 Phase phaseToUse,
                 String description,
                 int castingMod,
                 int sanityCost
    ) {
        super(e, name, CARD_TYPE, PRICE, ITEM_TYPE, hands, phaseToUse, description);
        this.castingMod = castingMod;
        this.sanityCost = sanityCost;
    }

    public int getCastingMod() {
        return castingMod;
    }

    public int getSanityCost() {
        return sanityCost;
    }
}
