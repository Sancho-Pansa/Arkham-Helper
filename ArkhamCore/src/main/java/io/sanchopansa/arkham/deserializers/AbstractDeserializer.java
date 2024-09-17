package io.sanchopansa.arkham.deserializers;

import com.google.gson.JsonObject;

public abstract class AbstractDeserializer {
    /**
     * Производит десериализацию указанного поля в JSON-объекте, если указанное поле существует.
     * Если не существует, вернет предписанное значение.
     * @param jsonObj Экземпляр JSONObject
     * @param field Имя-ключ поля JSON'а
     * @param or Резервное значение, если у указанного ключа нет значения.
     * @return Число из JSON, либо резервное значение
     */
    protected int optionallyDeserialize(JsonObject jsonObj, String field, int or) {
        return jsonObj.has(field) ? jsonObj.get(field).getAsInt() : or;
    }

    /**
     * Производит десериализацию указанного поля в JSON-объекте, если указанное поле существует.
     * Если не существует, вернет предписанное значение.
     * @param jsonObj Экземпляр JSONObject
     * @param field Имя-ключ поля JSON'а
     * @param or Резервное значение, если у указанного ключа нет значения.
     * @return Строка из JSON, либо резервное значение
     */
    protected String optionallyDeserialize(JsonObject jsonObj, String field, String or) {
        return jsonObj.has(field) ? jsonObj.get(field).getAsString() : or;
    }
}
