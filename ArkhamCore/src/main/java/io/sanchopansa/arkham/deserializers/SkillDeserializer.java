package io.sanchopansa.arkham.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;
import io.sanchopansa.arkham.cards.BonusStats;
import io.sanchopansa.arkham.cards.SkillCard;

import java.lang.reflect.Type;
import java.util.Optional;

public class SkillDeserializer extends AbstractDeserializer implements JsonDeserializer<SkillCard> {

    @Override
    public SkillCard deserialize(JsonElement jsonElement,
                                 Type type,
                                 JsonDeserializationContext jsonDeserializationContext
    ) throws JsonParseException {
        JsonObject jsonSkill = jsonElement.getAsJsonObject();

        String name = jsonSkill.get("name").getAsString();
        Expansion e = Expansion.fromString(jsonSkill.get("expansion").getAsString());

        BonusStats stats = optionallyDeserialize(jsonSkill, "stats");
        String passive = optionallyDeserialize(jsonSkill, "passive", "");
        Phase usablePhase = Phase.ANY;
        String activeDescription = "";

        Optional<JsonObject> active = Optional.ofNullable(jsonSkill.getAsJsonObject("active"));
        if(active.isPresent()) {
            usablePhase = Phase.from(active.get().get("phase").getAsString());
            activeDescription = active.get().get("description").getAsString();
        }
        return new SkillCard(e, name, stats, usablePhase, activeDescription, passive);
    }
}
