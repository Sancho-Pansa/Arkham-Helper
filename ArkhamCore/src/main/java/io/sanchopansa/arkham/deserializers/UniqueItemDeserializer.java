package io.sanchopansa.arkham.deserializers;

import io.sanchopansa.arkham.cards.UniqueItem;

public class UniqueItemDeserializer extends ItemDeserializerBridge {
    public UniqueItemDeserializer() {
        super(UniqueItem.class);
    }
}
