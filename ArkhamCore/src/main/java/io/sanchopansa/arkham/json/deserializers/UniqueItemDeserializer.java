package io.sanchopansa.arkham.json.deserializers;

import io.sanchopansa.arkham.core.cards.UniqueItem;

public final class UniqueItemDeserializer extends ItemDeserializerBridge {
    public UniqueItemDeserializer() {
        super(UniqueItem.class);
    }
}
