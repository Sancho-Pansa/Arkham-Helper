package io.sanchopansa.arkham.mechanics;

/**
 * Класс-рекорд, описывающий жетон Врат
 * @param world Иной мир
 * @param modifier Модификатор сложности закрытия
 * @param dSymbol Символ Иного мира
 */
public record Gate(String world, int modifier, DimensionSymbol dSymbol, Expansion expansion) {

}
