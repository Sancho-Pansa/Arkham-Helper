package io.sanchopansa.arkham;

import io.sanchopansa.arkham.locations.Location;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GameEngine {
    private final Game gameInstance;
    private final GameVault vault;
    private final Graph<Location, DefaultEdge> map;

    public GameEngine(Game gameInstance, GameVault vault, Graph<Location, DefaultEdge> map) {
        this.gameInstance = gameInstance;
        this.vault = vault;
        this.map = map;
    }


}
