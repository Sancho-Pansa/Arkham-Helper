package io.sanchopansa.arkham.json.deserializers;

import io.sanchopansa.arkham.core.cards.Spell;

public final class SpellDeserializer extends ItemDeserializerBridge {
    public SpellDeserializer() {
        super(Spell.class);
    }
}
