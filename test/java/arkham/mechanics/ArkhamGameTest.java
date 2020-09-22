package arkham.mechanics;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
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

    @Test
    public void testAncientTypes() {
        ArkhamGame ag = new ArkhamGame(5, new AncientOne(
                "Азатот",
                16,
                new HashSet<>(Arrays.asList(AncientOne.AncientTypes.NONE))
        ));
        AncientOne.AncientTypes a = null;
        for(AncientOne.AncientTypes x: ag.getAncientOne().getTypes())
            a = x;
        assertEquals(a, AncientOne.AncientTypes.NONE);
    }
}