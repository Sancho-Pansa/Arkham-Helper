package io.sanchopansa.arkham.json.deserializers;

import io.sanchopansa.arkham.cards.CommonItem;

public final class CommonItemDeserializer extends ItemDeserializerBridge {
    public CommonItemDeserializer() {
        super(CommonItem.class);
    }
}
