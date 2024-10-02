package io.sanchopansa.arkham.json.deserializers;

import io.sanchopansa.arkham.cards.Spell;

public final class SpellDeserializer extends ItemDeserializerBridge {
    public SpellDeserializer() {
        super(Spell.class);
    }
}
