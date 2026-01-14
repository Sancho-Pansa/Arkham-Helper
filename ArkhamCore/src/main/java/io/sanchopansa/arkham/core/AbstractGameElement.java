package io.sanchopansa.arkham.core;

public abstract class AbstractGameElement {
    protected Expansion expansion;

    public AbstractGameElement(Expansion e) {
        this.expansion = e;
    }

    public Expansion getExpansion() {
        return expansion;
    }
}
