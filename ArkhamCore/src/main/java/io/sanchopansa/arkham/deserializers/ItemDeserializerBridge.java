package io.sanchopansa.arkham.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;
import io.sanchopansa.arkham.cards.AbstractItem;
import io.sanchopansa.arkham.cards.CommonItem;
import io.sanchopansa.arkham.cards.Spell;
import io.sanchopansa.arkham.cards.UniqueItem;

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
        JsonObject jsonUniqueItem = jsonElement.getAsJsonObject();
        String name = jsonUniqueItem.get("name").getAsString();
        Expansion e = Expansion.fromString(jsonUniqueItem.get("expansion").getAsString());
        String itemType = jsonUniqueItem.get("itemType").getAsString();
        byte hands = (byte) optionallyDeserialize(jsonUniqueItem, "hands", 0);
        int cost = jsonUniqueItem.get("cost").getAsInt();
        String passive = optionallyDeserialize(jsonUniqueItem, "passive", "");
        Phase usablePhase = Phase.ANY;
        String activeDescription = "";

        Optional<JsonObject> active = Optional.ofNullable(jsonUniqueItem.getAsJsonObject("active"));
        if(active.isPresent()) {
            usablePhase = Phase.from(active.get().get("phase").getAsString());
            activeDescription = active.get().get("description").getAsString();
        }

        if(itemClass.getName().equals(CommonItem.class.getName())) {
            return new CommonItem(e, name, cost, itemType, hands, activeDescription, usablePhase, passive);
        } else if(itemClass.getName().equals(UniqueItem.class.getName())) {
            return new UniqueItem(e, name, cost, itemType, hands, activeDescription, usablePhase, passive);
        } else if(itemClass.getName().equals(Spell.class.getName())) {
            byte modifier = jsonUniqueItem.get("modifier").getAsByte();
            byte sanityCost = jsonUniqueItem.get("sanityCost").getAsByte();
            return new Spell(e, name, modifier, sanityCost, hands, usablePhase, activeDescription, passive);
        } else {
            return null;
        }
    }
}