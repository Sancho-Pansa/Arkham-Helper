package io.sanchopansa.arkham;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameFactoryTest {
    @Test
    public void FactoryTest() {
        new GameFactory().createVault();
        assertTrue(true);
    }
}