package io.sanchopansa.arkham.deserializers;

import io.sanchopansa.arkham.cards.UniqueItem;

public final class UniqueItemDeserializer extends ItemDeserializerBridge {
    public UniqueItemDeserializer() {
        super(UniqueItem.class);
    }
}
