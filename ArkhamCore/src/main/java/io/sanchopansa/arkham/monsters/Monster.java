package io.sanchopansa.arkham.monsters;

import io.sanchopansa.arkham.AbstractGameElement;
import io.sanchopansa.arkham.Expansion;

import java.util.List;

public class Monster extends AbstractGameElement {
    private final String name;
    private final Behavior behavior;
    private final int fleeModifier;
    private final boolean isElusive;
    private final Type type;
    private final DimensionSymbol dimension;
    private final int health;
    private final int sanityDamage;
    private final int compulsorySanityDamage;
    private final int healthDamage;
    private final int compulsoryHealthDamage;
    private final List<Ability> abilities;
    private final String flavor;

    public Monster(Expansion e,
                   String name,
                   Behavior behavior,
                   int fleeModifier,
                   boolean isElusive,
                   Type type,
                   DimensionSymbol dimension,
                   int health,
                   int sanityDamage,
                   int compulsorySanityDamage,
                   int healthDamage,
                   int compulsoryHealthDamage,
                   List<Ability> abilities,
                   String flavor
    ) {
        super(e);
        this.name = name;
        this.behavior = behavior;
        this.fleeModifier = fleeModifier;
        this.isElusive = isElusive;
        this.type = type;
        this.dimension = dimension;
        this.health = health;
        this.sanityDamage = sanityDamage;
        this.compulsorySanityDamage = compulsorySanityDamage;
        this.healthDamage = healthDamage;
        this.compulsoryHealthDamage = compulsoryHealthDamage;
        this.abilities = abilities;
        this.flavor = flavor;
    }

    public String getName() {
        return name;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public int getFleeModifier() {
        return fleeModifier;
    }

    public boolean isElusive() {
        return isElusive;
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

    public int getSanityDamage() {
        return sanityDamage;
    }

    public int getCompulsorySanityDamage() {
        return compulsorySanityDamage;
    }

    public int getHealthDamage() {
        return healthDamage;
    }

    public int getCompulsoryHealthDamage() {
        return compulsoryHealthDamage;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public String getFlavor() {
        return flavor;
    }

    public enum Behavior {
        NORMAL,
        STATIONARY,
        FAST,
        UNIQUE,
        FLYING,
        STALKER,
        AQUATIC
    }

    public enum Type {
        NORMAL,
        SPAWN,
        MASK
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

