package io.sanchopansa.arkham.core.cards;

import io.sanchopansa.arkham.core.Expansion;
import io.sanchopansa.arkham.core.Phase;

public final class Spell extends AbstractItem {
    private static final CardType CARD_TYPE = CardType.SPELL;
    private static final ItemType ITEM_TYPE = ItemType.NONE;
    private static final int PRICE = 0;

    private final int modifier;
    private final int sanityCost;

    public Spell(Expansion e,
                 String name,
                 int modifier,
                 int sanityCost,
                 int hands,
                 Phase usablePhase,
                 String description,
                 String passive
    ) {
        super(e, name, CARD_TYPE, BonusStats.createBuilder().build(), PRICE, ITEM_TYPE, hands, description, usablePhase, passive);
        this.modifier = modifier;
        this.sanityCost = sanityCost;
    }

    public int getModifier() {
        return modifier;
    }

    public int getSanityCost() {
        return sanityCost;
    }

    @Override
    public Spell cloneItem() {
        return new Spell(
                this.expansion,
                this.name,
                this.modifier,
                this.sanityCost,
                this.hands,
                this.active.usablePhase(),
                this.active.description(),
                this.passive
        );
    }
}
