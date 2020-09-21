package arkham.mechanics;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArkhamGameTest {

    @Test
    public void testGetState() {
        ArkhamGame ag = new ArkhamGame(5);
        ag.createGate(false);
        ag.addTerrorLevel();
        System.out.println(ag.getState());
        assertFalse(ag.isAwaken());
    }
}