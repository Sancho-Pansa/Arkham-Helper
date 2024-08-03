package io.sanchopansa.arkham.mechanics;

public abstract class AbstractCard {
    protected Expansion expansion;

    public AbstractCard(Expansion e) {
        this.expansion = e;
    }

    public Expansion getExpansion() {
        return expansion;
    }
}
