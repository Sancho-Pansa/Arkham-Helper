package io.sanchopansa.arkham.json.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.common.Expansion;
import io.sanchopansa.arkham.common.Phase;
import io.sanchopansa.arkham.cards.*;

import java.lang.reflect.Type;
import java.util.Optional;

public sealed class ItemDeserializerBridge extends AbstractDeserializer implements JsonDeserializer<AbstractItem>
        permits SpellDeserializer, CommonItemDeserializer, UniqueItemDeserializer {
    private final Class<? extends AbstractItem> itemClass;

    public ItemDeserializerBridge(Class<? extends AbstractItem> itemClass) {
        this.itemClass = itemClass;
    }

    @Override
    public AbstractItem deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonAbstractItem = jsonElement.getAsJsonObject();
        String name = jsonAbstractItem.get("name").getAsString();
        Expansion e = Expansion.fromString(jsonAbstractItem.get("expansion").getAsString());
        String itemType = optionallyDeserialize(jsonAbstractItem, "itemType", "NONE");
        byte hands = (byte) optionallyDeserialize(jsonAbstractItem, "hands", 0);
        int cost = optionallyDeserialize(jsonAbstractItem, "cost", 0);
        String passive = optionallyDeserialize(jsonAbstractItem, "passive", "");
        Phase usablePhase = Phase.ANY;
        String activeDescription = "";

        BonusStats stats = optionallyDeserialize(jsonAbstractItem, "stats");

        Optional<JsonObject> active = Optional.ofNullable(jsonAbstractItem.getAsJsonObject("active"));
        if(active.isPresent()) {
            usablePhase = Phase.from(active.get().get("phase").getAsString());
            activeDescription = active.get().get("description").getAsString();
        }

        if(itemClass.getName().equals(CommonItem.class.getName())) {
            return new CommonItem(e, name, cost, itemType, hands, stats, activeDescription, usablePhase, passive);
        } else if(itemClass.getName().equals(UniqueItem.class.getName())) {
            return new UniqueItem(e, name, cost, itemType, hands, stats, activeDescription, usablePhase, passive);
        } else if(itemClass.getName().equals(Spell.class.getName())) {
            byte modifier = jsonAbstractItem.get("modifier").getAsByte();
            byte sanityCost = jsonAbstractItem.get("sanityCost").getAsByte();
            return new Spell(e, name, modifier, sanityCost, hands, usablePhase, activeDescription, passive);
        } else {
            return null;
        }
    }
}