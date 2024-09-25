package io.sanchopansa.arkham;

import com.google.common.graph.MutableGraph;

@SuppressWarnings("UnstableApiUsage")
public abstract class AbstractGameFactory {
    public abstract GameVault createVault();
    public abstract MutableGraph<Location> createMap();
}
