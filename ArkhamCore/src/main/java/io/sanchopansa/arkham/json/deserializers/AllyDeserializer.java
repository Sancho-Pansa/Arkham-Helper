package io.sanchopansa.arkham.json.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.core.ActiveEffect;
import io.sanchopansa.arkham.core.Expansion;
import io.sanchopansa.arkham.core.Phase;
import io.sanchopansa.arkham.core.cards.Ally;
import io.sanchopansa.arkham.core.cards.BonusStats;

import java.lang.reflect.Type;
import java.util.Optional;

public class AllyDeserializer extends AbstractDeserializer implements JsonDeserializer<Ally> {

    @Override
    public Ally deserialize(JsonElement jsonElement,
                            Type type,
                            JsonDeserializationContext jsonDeserializationContext
    ) throws JsonParseException {
        JsonObject jsonAlly = jsonElement.getAsJsonObject();
        String name = jsonAlly.get("name").getAsString();
        Expansion e = Expansion.fromString(jsonAlly.get("expansion").getAsString());
        BonusStats bonusStats = optionallyDeserialize(jsonAlly, "stats");
        String passive = optionallyDeserialize(jsonAlly, "passive", "");
        String onDraw = optionallyDeserialize(jsonAlly, "onDraw", "");
        String onDiscard = optionallyDeserialize(jsonAlly, "onDiscard", "");

        Phase usablePhase = Phase.ANY;
        String activeDescription = "";
        Optional<JsonObject> active = Optional.ofNullable(jsonAlly.getAsJsonObject("active"));
        if(active.isPresent()) {
            usablePhase = Phase.from(active.get().get("phase").getAsString());
            activeDescription = active.get().get("description").getAsString();
        }

        return new Ally(e, name, bonusStats, new ActiveEffect(usablePhase, activeDescription), passive, onDraw, onDiscard);
    }
}
