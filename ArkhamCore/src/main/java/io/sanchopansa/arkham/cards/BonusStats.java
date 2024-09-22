package io.sanchopansa.arkham.cards;

/**
 * Дополнительные характеристики, которые могут выдать различные предметы.
 */
public record BonusStats(byte speed, byte sneak, byte fight, byte will, byte lore, byte luck, byte combat, byte horror,
                         byte evade, byte spell, byte stamina, byte sanity) {
    public static class Builder {
        private byte speed = 0;
        private byte sneak = 0;
        private byte fight = 0;
        private byte will = 0;
        private byte lore = 0;
        private byte luck = 0;
        private byte combat = 0;
        private byte horror = 0;
        private byte evade = 0;
        private byte spell = 0;
        private byte stamina = 0;
        private byte sanity = 0;

        public Builder speed(byte speed) {
            this.speed = speed;
            return this;
        }

        public Builder sneak(byte sneak) {
            this.sneak = sneak;
            return this;
        }

        public Builder fight(byte fight) {
            this.fight = fight;
            return this;
        }

        public Builder will(byte will) {
            this.will = will;
            return this;
        }

        public Builder lore(byte lore) {
            this.lore = lore;
            return this;
        }

        public Builder luck(byte luck) {
            this.luck = luck;
            return this;
        }

        public Builder combat(byte combat) {
            this.combat = combat;
            return this;
        }

        public Builder horror(byte horror) {
            this.horror = horror;
            return this;
        }

        public Builder evade(byte evade) {
            this.evade = evade;
            return this;
        }

        public Builder spell(byte spell) {
            this.spell = spell;
            return this;
        }

        public Builder stamina(byte stamina) {
            this.stamina = stamina;
            return this;
        }

        public Builder sanity(byte sanity) {
            this.sanity = sanity;
            return this;
        }

        public BonusStats build() {
            return new BonusStats(speed, sneak, fight, will, lore, luck, combat, horror, evade, spell, stamina, sanity);
        }
    }
}
