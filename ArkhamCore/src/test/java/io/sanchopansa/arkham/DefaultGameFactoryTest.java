package io.sanchopansa.arkham;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultGameFactoryTest {
    @Test
    public void FactoryTest() {
        new DefaultGameFactory().createVault();
        assertTrue(true);
    }
}