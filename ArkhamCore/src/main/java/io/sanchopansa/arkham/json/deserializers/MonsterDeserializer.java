package io.sanchopansa.arkham.json.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.common.Expansion;
import io.sanchopansa.arkham.monsters.Monster;

import java.lang.reflect.Type;
import java.util.List;

public class MonsterDeserializer extends AbstractDeserializer implements JsonDeserializer<Monster> {
    @Override
    public Monster deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonMonster = jsonElement.getAsJsonObject();

        String name = jsonMonster.get("name").getAsString();
        Expansion e = Expansion.fromString(jsonMonster.get("expansion").getAsString());
        String monsterType = optionallyDeserialize(jsonMonster, "type", "NORMAL");
        String behavior = jsonMonster.get("movement").getAsString();
        String symbol = jsonMonster.get("symbol").getAsString();
        String flavor = optionallyDeserialize(jsonMonster, "flavor", "");

        JsonObject stats = jsonMonster.getAsJsonObject("stats");
        int awareness = stats.get("awareness").getAsInt();
        int health = stats.get("hp").getAsInt();
        int horrorDamage = optionallyDeserialize(stats, "horrorDamage", 0);
        int combatDamage = optionallyDeserialize(stats, "combatDamage", 0);
        int horrorRating = optionallyDeserialize(stats, "horrorRating", 0);
        int combatRating = optionallyDeserialize(stats, "combatRating", 0);
        int overwhelming = optionallyDeserialize(stats, "overwhelming", 0);
        int nightmarish = optionallyDeserialize(stats, "nightmarish", 0);
        List<String> abilities = jsonMonster
                .get("abilities")
                .getAsJsonArray()
                .asList()
                .stream()
                .map(JsonElement::getAsString)
                .toList();

        return new Monster(
                e,
                name,
                behavior,
                awareness,
                monsterType,
                symbol,
                health,
                horrorRating,
                horrorDamage,
                nightmarish,
                combatRating,
                combatDamage,
                overwhelming,
                abilities,
                flavor
        );
    }
}