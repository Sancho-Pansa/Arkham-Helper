package io.sanchopansa.arkham;

/**
 * Фазы игры: Подготовка, Движение, Контакты в городе, Контакты в Иных мирах, Миф
 */
public enum Phase {
    UPKEEP,
    MOVEMENT,
    ARKHAM_ENCOUNTER,
    OTHER_WORLD_ENCOUNTER,
    MYTHOS,
    ANY
}
