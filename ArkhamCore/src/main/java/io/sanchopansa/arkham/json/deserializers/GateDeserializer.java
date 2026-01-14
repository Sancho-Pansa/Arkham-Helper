package io.sanchopansa.arkham.json.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.core.Expansion;
import io.sanchopansa.arkham.core.monsters.DimensionSymbol;
import io.sanchopansa.arkham.core.monsters.Gate;

import java.lang.reflect.Type;

public class GateDeserializer extends AbstractDeserializer implements JsonDeserializer<Gate> {
    @Override
    public Gate deserialize(JsonElement jsonElement,
                            Type type,
                            JsonDeserializationContext jsonDeserializationContext
    ) throws JsonParseException {
        JsonObject jsonMonster = jsonElement.getAsJsonObject();

        String world = jsonMonster.get("name").getAsString();
        Expansion e = Expansion.fromString(jsonMonster.get("expansion").getAsString());
        int modifier = jsonMonster.get("modifier").getAsInt();
        DimensionSymbol symbol = DimensionSymbol.of(jsonMonster.get("symbol").getAsString());

        return new Gate(
                e,
                world,
                modifier,
                symbol
        );
    }
}
