package io.sanchopansa.arkham.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;
import io.sanchopansa.arkham.cards.UniqueItem;

import java.lang.reflect.Type;
import java.util.Optional;

// NB: Использовать AbstractItem для десериализации слишком накладно (см. CommonItemDeserializer)
public class UniqueItemDeserializer implements JsonDeserializer<UniqueItem>  {
    @Override
    public UniqueItem deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonUniqueItem = jsonElement.getAsJsonObject();
        String name = jsonUniqueItem.get("name").getAsString();
        Expansion e = Expansion.fromString(jsonUniqueItem.get("expansion").getAsString());
        String itemType = jsonUniqueItem.get("itemType").getAsString();
        byte hands = Optional.of(jsonUniqueItem.get("hands").getAsByte()).orElse((byte) 0);
        int cost = jsonUniqueItem.get("cost").getAsInt();
        String passive = Optional.of(jsonUniqueItem.get("hands").getAsString()).orElse("");
        Phase usablePhase = Phase.ANY;
        String activeDescription = "";

        Optional<JsonObject> active = Optional.ofNullable(jsonUniqueItem.getAsJsonObject("active"));
        if(active.isPresent()) {
            usablePhase = Phase.from(active.get().get("phase").getAsString());
            activeDescription = active.get().get("description").getAsString();
        }
        return new UniqueItem(e, name, cost, itemType, hands, activeDescription, usablePhase, passive);
    }
}
