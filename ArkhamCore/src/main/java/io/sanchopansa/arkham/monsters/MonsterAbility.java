package io.sanchopansa.arkham.monsters;

public enum MonsterAbility {
    AMBUSH,
    ELUSIVE,
    ENDLESS,
    MAGICAL_IMMUNITY,
    MAGICAL_RESISTANCE,
    PHYSICAL_IMMUNITY,
    PHYSICAL_RESISTANCE,
    UNDEAD,
    WEAPON_IMMUNITY;

    public static MonsterAbility of(String name) {
        for(MonsterAbility x : MonsterAbility.values()) {
            if(x.name().toLowerCase().equals(name)) {
                return x;
            }
        }
        return null;
    }
}
