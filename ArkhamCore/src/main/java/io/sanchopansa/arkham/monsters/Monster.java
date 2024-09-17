package io.sanchopansa.arkham.monsters;

import io.sanchopansa.arkham.AbstractGameElement;
import io.sanchopansa.arkham.Expansion;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public final class Monster extends AbstractGameElement {
    private final String name;
    private final Behavior behavior;
    private final Type type;
    private final DimensionSymbol dimension;
    private final int awareness;
    private final int health;
    private final int horrorRating;
    private final int horrorDamage;
    private final int compulsoryHorrorDamage;
    private final int combatRating;
    private final int combatDamage;
    private final int compulsoryCombatDamage;
    private final List<Ability> abilities;
    private final String flavor;

    public Monster(Expansion e,
                   String name,
                   String behavior,
                   int awareness,
                   String type,
                   String dimension,
                   int health,
                   int horrorRating,
                   int horrorDamage,
                   int compulsoryHorrorDamage,
                   int combatRating,
                   int combatDamage,
                   int compulsoryCombatDamage,
                   List<String> abilities,
                   String flavor
    ) {
        super(e);
        this.name = name;
        this.behavior = Behavior.of(behavior);
        this.awareness = awareness;
        this.type = Type.valueOf(type);
        this.dimension = DimensionSymbol.of(dimension);
        this.health = health;
        this.horrorRating = horrorRating;
        this.horrorDamage = horrorDamage;
        this.compulsoryHorrorDamage = compulsoryHorrorDamage;
        this.combatRating = combatRating;
        this.combatDamage = combatDamage;
        this.compulsoryCombatDamage = compulsoryCombatDamage;
        this.abilities = abilities.stream().map(Ability::valueOf).toList();
        this.flavor = flavor;
    }

    public String getName() {
        return name;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public int getAwareness() {
        return awareness;
    }

    public Type getType() {
        return type;
    }

    public DimensionSymbol getDimension() {
        return dimension;
    }

    public int getHealth() {
        return health;
    }

    public int getHorrorRating() {
        return horrorRating;
    }

    public int getHorrorDamage() {
        return horrorDamage;
    }

    public int getCompulsoryHorrorDamage() {
        return compulsoryHorrorDamage;
    }

    public int getCombatRating() {
        return combatRating;
    }

    public int getCombatDamage() {
        return combatDamage;
    }

    public int getCompulsoryCombatDamage() {
        return compulsoryCombatDamage;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("\n\tname", name)
                .append("\n\texpansion", expansion)
                .append("\n\tbehavior", behavior)
                .append("\n\ttype", type)
                .append("\n\tdimension", dimension)
                .append("\n\tawareness", awareness)
                .append("\n\thealth", health)
                .append("\n\thorrorRating", horrorRating)
                .append("\n\thorrorDamage", horrorDamage)
                .append("\n\tcompulsoryHorrorDamage", compulsoryHorrorDamage)
                .append("\n\tcombatRating", combatRating)
                .append("\n\tcombatDamage", combatDamage)
                .append("\n\tcompulsoryCombatDamage", compulsoryCombatDamage)
                .append("\n\tabilities", abilities)
                .append("\n\tflavor", flavor)
                .toString();
    }

    public enum Behavior {
        NORMAL,
        STATIONARY,
        FAST,
        UNIQUE,
        FLYING,
        STALKER,
        AQUATIC;

        public static Behavior of(String name) {
            return Optional.of(Behavior.valueOf(name)).orElse(Behavior.NORMAL);
        }
    }

    public enum Type {
        NORMAL,
        SPAWN,
        MASK;

        public static Type of(String name) {
            return Optional.of(Type.valueOf(name)).orElse(Type.NORMAL);
        }
    }

    public enum Ability {
        AMBUSH,
        ELUSIVE,
        ENDLESS,
        MAGICAL_IMMUNITY,
        MAGICAL_RESISTANCE,
        PHYSICAL_IMMUNITY,
        PHYSICAL_RESISTANCE,
        WEAPON_IMMUNITY
    }
}

