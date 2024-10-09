package io.sanchopansa.arkham;

import io.sanchopansa.arkham.common.Phase;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.locations.Location;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GameEngine {
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GameEngine implements GameEngineInterface {
    private final Game gameInstance;
    private final GameVault vault;
    private final Graph<Location, DefaultEdge> map;

    public GameEngine(Game gameInstance, GameVault vault, Graph<Location, DefaultEdge> map) {
        this.gameInstance = gameInstance;
        this.vault = vault;
        this.map = map;
    }

    @Override
    public Game getGameInstance() {
        return gameInstance;
    }

    @Override
    public GameVault getVault() {
        return vault;
    }

    @Override
    public Graph<Location, DefaultEdge> getMap() {
        return map;
    }

    @Override
    public void launch() {

    }

    @Override
    public void upkeep() {
    }

    @Override
    public void movement() {
    }

    @Override
    public void encounters() {
    }

    @Override
    public void otherWorld() {
    }

    @Override
    public void mythos() {

    }
}
