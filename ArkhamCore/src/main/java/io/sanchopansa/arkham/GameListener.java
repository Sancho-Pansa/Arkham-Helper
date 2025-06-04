package io.sanchopansa.arkham;

import io.sanchopansa.arkham.common.Phase;

/**
 * Интерфейс-коллбэк для общения с UI
 */
public interface GameListener {
    /**
     * Вызывается на старте Фазы.
     * @param gameState Состояние игры (в т.ч. и фаза)
     * @param gameVault Состояние коробки
     */
    void onPhaseStart(Game gameState, GameVault gameVault);

    void onGameWin();
    void onGameOver();
}
