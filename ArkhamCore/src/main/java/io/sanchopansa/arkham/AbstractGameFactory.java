package io.sanchopansa.arkham;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public abstract class AbstractGameFactory {
    public abstract GameVault createVault();
    public abstract Graph<Location, DefaultEdge> createMap();
}
