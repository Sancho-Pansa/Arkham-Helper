package io.sanchopansa.arkham.json.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.common.Expansion;
import io.sanchopansa.arkham.locations.Location;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

public class LocationDeserializer extends AbstractDeserializer implements JsonDeserializer<Map<String, Location>> {

    @Override
    public Map<String, Location> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return jsonElement
                .getAsJsonObject()
                .asMap()
                .entrySet()
                .stream()
                .collect(Collectors
                        .toMap(
                                Map.Entry::getKey,
                                entry -> deserializeLocationMetadata(entry
                                        .getValue()
                                        .getAsJsonObject()
                                        .get("metadata")
                                        .getAsJsonObject()
                                )
                        ));
    }

    private Location deserializeLocationMetadata(JsonObject locationJson) {
        String name = locationJson.get("name").getAsString();
        String description = optionallyDeserialize(locationJson, "description", "");
        boolean isStable = !locationJson.has("stable") || locationJson.get("stable").getAsBoolean();
        boolean isStreet = locationJson.has("isStreet") && locationJson.get("isStreet").getAsBoolean();

        return new Location(Expansion.VANILLA, name, description, isStreet, isStable);
    }
}
