package io.sanchopansa.arkham.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;
import io.sanchopansa.arkham.investigators.Investigator;

import java.lang.reflect.Type;

public class InvestigatorDeserializer implements JsonDeserializer<Investigator> {
    @Override
    public Investigator deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonInvestigator = jsonElement.getAsJsonObject();
        String name = jsonInvestigator.get("name").getAsString();
        String title = jsonInvestigator.get("title").getAsString();
        Expansion e = Expansion.fromString(jsonInvestigator.get("expansion").getAsString());

        int stamina = jsonInvestigator.get("stamina").getAsInt();
        int sanity = jsonInvestigator.get("sanity").getAsInt();

        int minSpeed = jsonInvestigator.get("sanity").getAsInt();
        int maxSneak = jsonInvestigator.get("maxSneak").getAsInt();
        int minFight = jsonInvestigator.get("minFight").getAsInt();
        int maxWill = jsonInvestigator.get("maxWill").getAsInt();
        int minLore = jsonInvestigator.get("minLore").getAsInt();
        int maxLuck = jsonInvestigator.get("maxLuck").getAsInt();

        int focus = jsonInvestigator.get("maxLuck").getAsInt();

        JsonObject ability = jsonInvestigator.get("ability").getAsJsonObject();
        String abilityName = ability.get("name").getAsString();
        String abilityDescription = ability.get("description").getAsString();
        Phase abilityPhase = Phase.valueOf(ability.get("phase").getAsString());
        return new Investigator(
                e,
                name,
                title,
                stamina,
                sanity,
                focus,
                minSpeed,
                maxSneak,
                minFight,
                maxWill,
                minLore,
                maxLuck,
                abilityName,
                abilityDescription,
                abilityPhase
        );
    }
}
