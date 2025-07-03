package io.sanchopansa.arkham.cards;

/**
 * Дополнительные характеристики, которые могут выдать различные предметы.
 */
public record BonusStats(
        int speed, int sneak, int fight, int will, int lore, int luck, int combat, int horror,
        int evade, int spell, int stamina, int sanity
) {
    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int speed = 0;
        private int sneak = 0;
        private int fight = 0;
        private int will = 0;
        private int lore = 0;
        private int luck = 0;
        private int combat = 0;
        private int horror = 0;
        private int evade = 0;
        private int spell = 0;
        private int stamina = 0;
        private int sanity = 0;

        private Builder() {

        }

        public Builder speed(int speed) {
            this.speed = speed;
            return this;
        }

        public Builder sneak(int sneak) {
            this.sneak = sneak;
            return this;
        }

        public Builder fight(int fight) {
            this.fight = fight;
            return this;
        }

        public Builder will(int will) {
            this.will = will;
            return this;
        }

        public Builder lore(int lore) {
            this.lore = lore;
            return this;
        }

        public Builder luck(int luck) {
            this.luck = luck;
            return this;
        }

        public Builder combat(int combat) {
            this.combat = combat;
            return this;
        }

        public Builder horror(int horror) {
            this.horror = horror;
            return this;
        }

        public Builder evade(int evade) {
            this.evade = evade;
            return this;
        }

        public Builder spell(int spell) {
            this.spell = spell;
            return this;
        }

        public Builder stamina(int stamina) {
            this.stamina = stamina;
            return this;
        }

        public Builder sanity(int sanity) {
            this.sanity = sanity;
            return this;
        }

        public BonusStats build() {
            return new BonusStats(speed, sneak, fight, will, lore, luck, combat, horror, evade, spell, stamina, sanity);
        }
    }
}
