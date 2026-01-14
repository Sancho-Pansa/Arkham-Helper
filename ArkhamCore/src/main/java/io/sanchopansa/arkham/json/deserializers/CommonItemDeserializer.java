package io.sanchopansa.arkham.json.deserializers;

import io.sanchopansa.arkham.core.cards.CommonItem;

public final class CommonItemDeserializer extends ItemDeserializerBridge {
    public CommonItemDeserializer() {
        super(CommonItem.class);
    }
}
