package io.sanchopansa.arkham.json.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.core.Expansion;
import io.sanchopansa.arkham.core.monsters.Ancient;
import io.sanchopansa.arkham.core.monsters.Monster;
import io.sanchopansa.arkham.core.monsters.MonsterAbility;
import io.sanchopansa.arkham.core.monsters.MonsterBehavior;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AncientDeserializer extends AbstractDeserializer implements JsonDeserializer<Ancient> {

    private final Set<Monster> monstersPool;

    public AncientDeserializer(Set<Monster> monstersPool) {
        this.monstersPool = monstersPool;
    }

    @Override
    public Ancient deserialize(JsonElement jsonElement,
                               Type type,
                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonAncient = jsonElement.getAsJsonObject();

        String name = jsonAncient.get("name").getAsString();
        Expansion e = Expansion.fromString(jsonAncient.get("expansion").getAsString());
        int doomTrack = jsonAncient.get("doomTrack").getAsInt();
        int combat = jsonAncient.get("combat").getAsInt();
        String battleStart = optionallyDeserialize(jsonAncient, "battleStart", "");
        String attackDescription = optionallyDeserialize(jsonAncient, "attackDescription", "");

        JsonObject powerJson = jsonAncient.get("power").getAsJsonObject();
        String powerName = powerJson.get("name").getAsString();
        String powerDescription = powerJson.get("description").getAsString();

        Set<MonsterAbility> monsterTypes = new HashSet<>();
        if(jsonAncient.has("monsterType")) {
            monsterTypes.addAll(jsonAncient.get("monsterType").getAsJsonArray()
                    .asList()
                    .stream()
                    .map(t -> MonsterAbility.of(t.getAsString()))
                    .collect(Collectors.toSet()));
        }

        // Последователи
        JsonArray worshippersListJson = jsonAncient.get("worshippers").getAsJsonArray();
        Set<Ancient.Worshipper> worshippers = worshippersListJson.asList()
                .stream()
                .map(JsonElement::getAsJsonObject)
                .map(this::deserializeWorshipper)
                .collect(Collectors.toSet());


        return new Ancient(
                e,
                name,
                doomTrack,
                combat,
                worshippers,
                monsterTypes,
                powerName,
                powerDescription,
                battleStart,
                attackDescription
        );
    }

    private Ancient.Worshipper deserializeWorshipper(JsonObject worshipperJson) throws JsonParseException {
        Monster worshipper = null;
        for(Monster x : monstersPool) {
            if(x.getName().equals(worshipperJson.get("name").getAsString())) {
                worshipper = x;
                break;
            }
        }
        if(worshipper == null) {
            throw new JsonParseException("Error in Worshippers field of the Ancient");
        }

        JsonObject bonusesJson = worshipperJson.get("bonuses").getAsJsonObject();
        JsonArray abilitiesJsonArray = bonusesJson.getAsJsonArray("bonusAbility");
        Set<MonsterAbility> abilities = new HashSet<>();
        if(bonusesJson.has("bonusAbility")) {
            abilities.addAll(abilitiesJsonArray
                    .asList()
                    .stream()
                    .map(JsonElement::getAsString)
                    .map(MonsterAbility::of)
                    .collect(Collectors.toSet()));
        }

        MonsterBehavior behavior = null;
        if(bonusesJson.has("movementType")) {
            behavior = MonsterBehavior.of(bonusesJson.get("movementType").getAsString());
        }

        int hp = Integer.MIN_VALUE;
        int horrorDamage = Integer.MIN_VALUE;
        int horrorRating = Integer.MIN_VALUE;
        int combatDamage = Integer.MIN_VALUE;
        int combatRating = Integer.MIN_VALUE;

        if(bonusesJson.has("stats")) {
            JsonObject statsJson = bonusesJson.get("stats").getAsJsonObject();
            Function<String, Integer> checkStat = field -> statsJson.has(field) ? statsJson.get(field).getAsInt() : Integer.MIN_VALUE;

            hp = checkStat.apply("hp");
            horrorDamage = checkStat.apply("horrorDamage");
            horrorRating = checkStat.apply("horrorRating");
            combatDamage = checkStat.apply("combatDamage");
            combatRating = checkStat.apply("combatRating");
        }

        return new Ancient.Worshipper(
                worshipper,
                new Ancient.WorshipperBonus(
                        abilities,
                        behavior,
                        hp,
                        horrorDamage,
                        horrorRating,
                        combatDamage,
                        combatRating
                )
        );
    }
}
