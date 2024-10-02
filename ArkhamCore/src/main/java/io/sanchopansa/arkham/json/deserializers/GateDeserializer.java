package io.sanchopansa.arkham.json.deserializers;

import com.google.gson.*;
import io.sanchopansa.arkham.common.Expansion;
import io.sanchopansa.arkham.monsters.DimensionSymbol;
import io.sanchopansa.arkham.monsters.Gate;

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
        byte modifier = jsonMonster.get("modifier").getAsByte();
        DimensionSymbol symbol = DimensionSymbol.of(jsonMonster.get("symbol").getAsString());

        return new Gate(
                e,
                world,
                modifier,
                symbol
        );
    }
}
