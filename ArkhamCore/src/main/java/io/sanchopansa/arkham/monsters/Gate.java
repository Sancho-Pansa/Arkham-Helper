package io.sanchopansa.arkham.monsters;

import io.sanchopansa.arkham.AbstractGameElement;
import io.sanchopansa.arkham.Expansion;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Класс-рекорд, описывающий жетон Врат
 */
public final class Gate extends AbstractGameElement implements Cloneable {
    private final String world;
    private final byte modifier;
    private final DimensionSymbol symbol;

    /**
     * @param world    Иной мир
     * @param modifier Модификатор сложности закрытия
     * @param dSymbol  Символ Иного мира
     */
    public Gate(Expansion e, String world, byte modifier, DimensionSymbol dSymbol) {
        super(e);
        this.world = world;
        this.modifier = modifier;
        this.symbol = dSymbol;
    }

    public Gate(Gate original) {
        super(original.expansion);
        this.world = original.world;
        this.modifier = original.modifier;
        this.symbol = original.symbol;
    }

    public String getWorld() {
        return world;
    }

    public byte getModifier() {
        return modifier;
    }

    public DimensionSymbol getDimension() {
        return symbol;
    }

    @Override
    public Gate clone() throws CloneNotSupportedException {
        return new Gate(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Gate) obj;
        return Objects.equals(this.world, that.world) &&
                this.modifier == that.modifier &&
                Objects.equals(this.symbol, that.symbol) &&
                Objects.equals(this.expansion, that.expansion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, modifier, symbol, expansion);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("\t\nworld", world)
                .append("\t\nexpansion", expansion)
                .append("\t\nmodifier", modifier)
                .append("\t\nsymbol", symbol)
                .append("\n")
                .toString();
    }
}
