package io.sanchopansa.arkham;

import com.google.common.base.Enums;

/**
 * Фазы игры: Подготовка, Движение, Контакты в городе, Контакты в Иных мирах, Миф
 */
public enum Phase {
    UPKEEP,
    MOVEMENT,
    ARKHAM_ENCOUNTER,
    OTHER_WORLD_ENCOUNTER,
    MYTHOS,
    ANY;

    public static Phase from(String value) {
        return Enums.getIfPresent(Phase.class, value).or(Phase.ANY);
    }
}
