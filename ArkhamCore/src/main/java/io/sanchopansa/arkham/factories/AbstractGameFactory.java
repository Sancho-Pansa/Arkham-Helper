package io.sanchopansa.arkham.factories;

import io.sanchopansa.arkham.GameVault;
import io.sanchopansa.arkham.locations.Location;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public abstract class AbstractGameFactory {
    public abstract GameVault createVault() throws Exception;
    public abstract Graph<Location, DefaultEdge> createMap() throws Exception;
}
