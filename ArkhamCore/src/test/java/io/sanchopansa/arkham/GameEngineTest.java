package io.sanchopansa.arkham;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import io.sanchopansa.arkham.factories.AbstractGameFactory;
import io.sanchopansa.arkham.factories.JsonGameFactory;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.Ancient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class GameEngineTest {
    @Test
    public void testGameLaunch() throws Exception {
        AbstractGameFactory factory = new JsonGameFactory();
        var vault = factory.createVault();
        Multiset<Ancient> ancients = HashMultiset.create(vault.getAncientsPool());
        Multiset<Investigator> investigators = HashMultiset.create(vault.getInvestigators());
        Iterator<Investigator> iterator = investigators.iterator();
        Game game = new Game(
                ancients.iterator().next(),
                iterator.next(),
                iterator.next(),
                iterator.next(),
                iterator.next()
        );
        GameEngine engine = new GameEngine(game, vault, factory.createMap());
        engine.launch();
        Assertions.assertTrue(true);
    }
}