package io.sanchopansa.arkham;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class GameFactoryTest {
    @Test
    public void ioTest() {
        String s = "1.json";
        try(
                InputStream is = this.getClass().getClassLoader().getResourceAsStream(s);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);) {
            br.lines().forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path p = Paths.get("src/main/resources/1.json");
        assertTrue(true);
    }

}