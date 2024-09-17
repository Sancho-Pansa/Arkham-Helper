package io.sanchopansa.arkham.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;
import io.sanchopansa.arkham.cards.CommonItem;

import java.lang.reflect.Type;
import java.util.Optional;

/*
    NB: Попробовал сделать более широкий десериализатор, чтобы не дублировать код для UniqueItem, но он выходит слишком
        грузным, так что пришлось отказаться от этой идеи.
 */
public class CommonItemDeserializer implements JsonDeserializer<CommonItem> {

    @Override
    public CommonItem deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonCommonItem = jsonElement.getAsJsonObject();
        String name = jsonCommonItem.get("name").getAsString();
        Expansion e = Expansion.fromString(jsonCommonItem.get("expansion").getAsString());
        String itemType = jsonCommonItem.get("itemType").getAsString();
        byte hands = Optional.of(jsonCommonItem.get("hands").getAsByte()).orElse((byte) 0);
        int cost = jsonCommonItem.get("cost").getAsInt();
        String passive = Optional.of(jsonCommonItem.get("hands").getAsString()).orElse("");
        Phase usablePhase = Phase.ANY;
        String activeDescription = "";

        Optional<JsonObject> active = Optional.ofNullable(jsonCommonItem.getAsJsonObject("active"));
        if(active.isPresent()) {
            usablePhase = Phase.from(active.get().get("phase").getAsString());
            activeDescription = active.get().get("description").getAsString();
        }
        return new CommonItem(e, name, cost, itemType, hands, activeDescription, usablePhase, passive);
    }
}
