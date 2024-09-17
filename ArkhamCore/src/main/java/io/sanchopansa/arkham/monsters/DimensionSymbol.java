package io.sanchopansa.arkham.monsters;

import java.util.Optional;

/**
 * Список символов Иных миров у монстров и врат, а также у карт Мифа и др. механик
 */
public enum DimensionSymbol {
    CRESCENT,
    CIRCLE,
    DIAMOND,
    HEXAGON,
    CROSS,
    SLASH,
    SQUARE,
    STAR,
    TRIANGLE;

    public static DimensionSymbol of(String symbol) {
        return Optional.of(DimensionSymbol.valueOf(symbol)).orElse(DimensionSymbol.CRESCENT);
    }
}
