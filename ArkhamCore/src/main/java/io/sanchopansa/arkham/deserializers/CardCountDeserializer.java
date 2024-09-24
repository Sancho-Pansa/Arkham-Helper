package io.sanchopansa.arkham.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Десериализует JSON'ы, в которых указано количество карт, кототорые должны быть в игре.
 */
public class CardCountDeserializer extends AbstractDeserializer implements JsonDeserializer<Map<String, Integer>> {


    @Override
    public Map<String, Integer> deserialize(JsonElement jsonElement,
                                            Type type,
                                            JsonDeserializationContext jsonDeserializationContext
    ) throws JsonParseException {
        return jsonElement.getAsJsonArray()
                .asList()
                .stream()
                .map(JsonElement::getAsJsonObject)
                .filter(e -> e.has("count") && e.has("name"))
                .collect(
                        Collectors.toMap(
                                e -> e.get("name").getAsString(),
                                e -> e.get("count").getAsInt()
                        )
                );

    }
}
