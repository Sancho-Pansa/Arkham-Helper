package io.sanchopansa.arkham.mechanics;

import io.sanchopansa.arkham.investigators.Stat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StatTest {
    @Test
    public void testFields() {
        int maximum = 4;
        Stat stat = new Stat(maximum);
        assertEquals(0, stat.getCurrentMinimum());
        assertEquals(0, stat.getInitialMinimum());
        assertEquals(maximum, stat.getCurrentMaximum());
        assertEquals(maximum, stat.getInitialMaximum());
        assertEquals(maximum, stat.getValue());

        Stat secondStat = new Stat(1, 10);
        assertEquals(1, secondStat.getCurrentMinimum());
        assertEquals(1, secondStat.getInitialMinimum());

        assertEquals(10, secondStat.getInitialMaximum());
        assertEquals(10, secondStat.getCurrentMaximum());

        assertEquals(10, secondStat.getValue());
    }

    @Test
    public void testMethods() {
        int maximum = 5;
        Stat stat = new Stat(maximum);
        stat.setCurrentMinimum(1);
        assertEquals(1, stat.getCurrentMinimum());
        assertEquals(0, stat.getInitialMinimum());

        stat.refill();
        assertEquals(stat.getCurrentMaximum(), stat.getValue());

        stat.add(1000);
        assertEquals(stat.getCurrentMaximum(), stat.getValue());
    }
}
