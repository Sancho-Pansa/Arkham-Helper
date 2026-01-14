package io.sanchopansa.arkham.factories;

import io.sanchopansa.arkham.core.GameVault;
import io.sanchopansa.arkham.core.locations.Location;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public abstract class AbstractGameFactory {
    public abstract GameVault createVault() throws Exception;
    public abstract Graph<Location, DefaultEdge> createMap() throws Exception;
}
