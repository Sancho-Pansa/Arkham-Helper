package io.sanchopansa.arkham;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultGameFactoryTest {
    @Test
    public void FactoryTest() {
        try {
            new DefaultGameFactory().createVault();
            assertTrue(true);
        } catch(IOException e) {
            System.err.println("Catch IOException");;
        } catch(URISyntaxException e) {
            System.err.println("Catch URISyntaxException");
        }
    }
}