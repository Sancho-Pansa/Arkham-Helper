package io.sanchopansa.arkham;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameFactoryTest {
    @Test
    public void FactoryTest() {
        new GameFactory().createVault();
        assertTrue(true);
    }
}