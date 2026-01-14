package io.sanchopansa.arkham.json.deserializers;

import com.google.gson.JsonObject;
import io.sanchopansa.arkham.core.cards.BonusStats;

public abstract class AbstractDeserializer {
    /**
     * Производит десериализацию указанного поля в JSON-объекте, если указанное поле существует.
     * Если не существует, вернет предписанное значение.
     *
     * @param jsonObj Экземпляр JSONObject
     * @param field   Имя-ключ поля JSON'а
     * @param or      Резервное значение, если у указанного ключа нет значения.
     * @return Число из JSON, либо резервное значение
     */
    protected int optionallyDeserialize(JsonObject jsonObj, String field, int or) {
        return jsonObj.has(field) ? jsonObj.get(field).getAsInt() : or;
    }

    /**
     * Производит десериализацию указанного поля в JSON-объекте, если указанное поле существует.
     * Если не существует, вернет предписанное значение.
     *
     * @param jsonObj Экземпляр JSONObject
     * @param field   Имя-ключ поля JSON'а
     * @param or      Резервное значение, если у указанного ключа нет значения.
     * @return Строка из JSON, либо резервное значение
     */
    protected String optionallyDeserialize(JsonObject jsonObj, String field, String or) {
        return jsonObj.has(field) ? jsonObj.get(field).getAsString() : or;
    }

    protected BonusStats optionallyDeserialize(JsonObject jsonObj, String field) {
        if(jsonObj.has(field)) {
            JsonObject statsJson = jsonObj.getAsJsonObject(field);
            var statsBuilder = BonusStats.createBuilder();

            statsBuilder
                    .speed(optionallyDeserialize(statsJson, "speed", 0))
                    .sneak(optionallyDeserialize(statsJson, "sneak", 0))
                    .fight(optionallyDeserialize(statsJson, "fight", 0))
                    .will(optionallyDeserialize(statsJson, "will", 0))
                    .lore(optionallyDeserialize(statsJson, "lore", 0))
                    .luck(optionallyDeserialize(statsJson, "luck", 0))
                    .combat(optionallyDeserialize(statsJson, "combat", 0))
                    .horror(optionallyDeserialize(statsJson, "horror", 0))
                    .evade(optionallyDeserialize(statsJson, "evade", 0))
                    .spell(optionallyDeserialize(statsJson, "spell", 0))
                    .stamina(optionallyDeserialize(statsJson, "stamina", 0))
                    .sanity(optionallyDeserialize(statsJson, "sanity", 0));

            return statsBuilder.build();
        } else {
            return BonusStats.createBuilder().build();
        }
    }
}
