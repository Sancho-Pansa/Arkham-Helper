package io.sanchopansa.arkham.monsters;

import java.util.Optional;

public enum MonsterBehavior {
    NORMAL,
    STATIONARY,
    FAST,
    UNIQUE,
    FLYING,
    STALKER,
    AQUATIC;

    public static MonsterBehavior of(String name) {
        return Optional.of(MonsterBehavior.valueOf(name)).orElse(MonsterBehavior.NORMAL);
    }
}
