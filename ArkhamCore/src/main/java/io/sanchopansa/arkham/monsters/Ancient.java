package io.sanchopansa.arkham.monsters;

import io.sanchopansa.arkham.AbstractGameElement;
import io.sanchopansa.arkham.Expansion;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.Set;

/**
 * Класс, описывающий Древнего
 *
 * @author SanchoPansa
 */

public class Ancient extends AbstractGameElement {
    private final String name;
    private final int doomTrack;
    private final int combat;
    private final Set<MonsterAbility> monsterAbilities;
    private final Set<Worshipper> worshippers;
    private final String powerName;
    private final String powerDescription;
    private final String battleStart;
    private final String attackDescription;

    public Ancient(Expansion e,
                   String name,
                   int doomTrack,
                   int combat,
                   Set<Worshipper> worshipper,
                   Set<MonsterAbility> monsterAbilities,
                   String powerName,
                   String powerDescription,
                   String battleStart,
                   String attackDescription) {
        super(e);
        this.name = name;
        this.doomTrack = doomTrack;
        this.combat = combat;
        this.worshippers = worshipper;
        this.monsterAbilities = monsterAbilities;
        this.powerName = powerName;
        this.powerDescription = powerDescription;
        this.battleStart = battleStart;
        this.attackDescription = attackDescription;
    }

    public String getName() {
        return name;
    }

    public int getDoomTrack() {
        return doomTrack;
    }

    public int getCombat() {
        return combat;
    }

    public Set<Worshipper> getWorshippers() {
        return worshippers;
    }

    public Set<MonsterAbility> getMonsterAbilities() {
        return monsterAbilities;
    }

    public String getPowerName() {
        return powerName;
    }

    public String getPowerDescription() {
        return powerDescription;
    }

    public String getBattleStart() {
        return battleStart;
    }

    public String getAttackDescription() {
        return attackDescription;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Ancient that = (Ancient) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, doomTrack);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("\n\tname", name)
                .append("\n\texpansion", expansion)
                .append("\n\tdoomTrack", doomTrack)
                .append("\n\tcombat", combat)
                .append("\n\tworshipper", worshippers)
                .append("\n\tpowerName", powerName)
                .append("\n\tpowerDescription", powerDescription)
                .append("\n\tbattleStart", battleStart)
                .append("\n\tattackDescription", attackDescription)
                .append("\n")
                .toString();
    }

    /**
     * Обозначает бонусы, которые Древний предоставляет последователям
     * @param ability Дополнительные способности Монстра
     * @param behavior Новый тип движения Монстра (перезаписывает старый)
     * @param hp Новое здоровье Монстра (-128 зарезервировано для отсутствия бонуса во избежание NPE)
     * @param horrorDamage ditto
     * @param horrorRating ditto
     * @param combatDamage ditto
     * @param combatRating ditto
     */
    public record WorshipperBonus(
            Set<MonsterAbility> ability,
            MonsterBehavior behavior,
            int hp,
            int horrorDamage,
            int horrorRating,
            int combatDamage,
            int combatRating
    ) {
    }

    /**
     * Обозначает последователя-Монстра и набор бонусов, предоставляемых ему его Древним.
     * @param worshipper
     * @param bonus
     */
    public record Worshipper(Monster worshipper, WorshipperBonus bonus) {
    }
}
