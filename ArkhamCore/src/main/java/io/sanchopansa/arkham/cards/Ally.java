package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.common.ActiveEffect;
import io.sanchopansa.arkham.common.Expansion;
import org.apache.commons.lang3.builder.ToStringBuilder;

public final class Ally extends AbstractCard {
    private static final CardType CARD_TYPE = CardType.ALLY;

    private final String onDraw;
    private final String onDiscard;

    public Ally(Expansion e, String name, BonusStats bonusStats, ActiveEffect active, String passive, String onDraw, String onDiscard) {
        super(e, name, CARD_TYPE, bonusStats, active, passive);
        this.onDraw = onDraw;
        this.onDiscard = onDiscard;
    }

    public String getOnDraw() {
        return onDraw;
    }

    public String getOnDiscard() {
        return onDiscard;
    }

    @Override
    public Ally clone() {
        return new Ally(
                this.expansion,
                this.name,
                this.bonusStats,
                this.active,
                this.passive,
                this.onDraw,
                this.onDiscard
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("\n\tname", name)
                .append("\n\texpansion", expansion)
                .append("\n\tcardType", cardType)
                .append("\n\tonDraw", onDraw)
                .append("\n\tonDiscard", onDiscard)
                .append("\n\tactive", active)
                .append("\n\tpassive", passive)
                .append("\n\tbonusStats", bonusStats)
                .append("\n")
                .toString();
    }
}
