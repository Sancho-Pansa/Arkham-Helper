package io.sanchopansa.arkham.cards;

import io.sanchopansa.arkham.ActiveEffect;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;
import org.apache.commons.lang3.builder.ToStringBuilder;

public final class SkillCard extends AbstractCard {
    private static final CardType CARD_TYPE = CardType.SKILL;

    public SkillCard(Expansion e, String name, BonusStats bonusStats, Phase phaseToUse, String active, String passive) {
        super(e, name, CARD_TYPE, bonusStats, active, phaseToUse, passive);
    }

    public SkillCard(Expansion e, String name, BonusStats bonusStats, ActiveEffect active, String passive) {
        super(e, name, CARD_TYPE, bonusStats, active, passive);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("\n\tname", name)
                .append("\n\texpansion", expansion)
                .append("\n\tbonusStats", bonusStats)
                .append("\n\tactive", active)
                .append("\n\tpassive", passive)
                .append("\n")
                .toString();
    }

    @Override
    public SkillCard clone() {
        return new SkillCard(
                this.expansion,
                this.name,
                this.bonusStats,
                this.active,
                this.passive
        );
    }
}
