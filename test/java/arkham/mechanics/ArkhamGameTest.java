package arkham.mechanics;

import arkham.json.AncientOneJson;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArkhamGameTest {

    private AncientOne ao;
    private ArkhamGame game;

    @Before
    public void initializeAO() {
        AncientOneJson aoJson = new AncientOneJson();
        ao = aoJson.extractAO("Ктулху");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongPlayersCount() {
        game = new ArkhamGame(9, ao);
    }

    @Test
    public void testPlayerDependentValues() {
        game = new ArkhamGame(3, ao);
        assertEquals(7, game.getGateLimit());
        assertEquals(6, game.getMonsterLimit()); // 3 + 3
        assertEquals(5, game.getOutskirtsLimit()); // 8 - 3
    }

    @Test
    public void testMonsterSurge() {
        game = new ArkhamGame(3, ao);
        game.createGate(false); // +1 Monster
        game.createGate(true); // +3 Monsters (max of 3 players and 2 gates)
        assertEquals(4, game.getMapMonsterCount());
    }

    @Test
    public void testGateCount() {
        game = new ArkhamGame(4, ao);
        game.createGate(false);
        game.createGate(false);
        game.createGate(true);
        assertEquals(2, game.getGateCount());
    }
}