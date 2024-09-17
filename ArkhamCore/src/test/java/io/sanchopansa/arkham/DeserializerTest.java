package io.sanchopansa.arkham;

import com.google.gson.GsonBuilder;
import io.sanchopansa.arkham.cards.CommonItem;
import io.sanchopansa.arkham.deserializers.InvestigatorDeserializer;
import io.sanchopansa.arkham.investigators.Investigator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DeserializerTest {
    static Investigator[] investigators;
    static CommonItem[] commonItems;

    @BeforeAll
    public static void setUpBeforeClass() throws IOException, URISyntaxException {
        GsonBuilder gson = new GsonBuilder();
        var investigatorCollection = new DeserializerTest()
                .getStreamFromResourcesFile("Investigators.json")
                .collect(Collectors.joining());
        gson.registerTypeAdapter(Investigator.class, new InvestigatorDeserializer());
        investigators = gson.create().fromJson(investigatorCollection, Investigator[].class);

        var commonItemsJson = new DeserializerTest()
                .getStreamFromResourcesFile("CommonItems.json")
                .collect(Collectors.joining());
        gson.registerTypeAdapter(Investigator.class, new InvestigatorDeserializer());
        commonItems = gson.create().fromJson(commonItemsJson, CommonItem[].class);
    }

    @Test
    public void InvestigatorDeserializationTest() {
        assertTrue(investigators.length > 0, "Array of investigators is not empty");
    }

    @Test
    public void CommonItemDeserializationTest() {
        assertTrue(commonItems.length > 0, "Array of Common Items is not empty");
    }

    /**
     * @param filename Имя JSON'а (корневой директорией считается resources/)
     * @return Стрим из файла
     * @throws NullPointerException Если файла не существует
     * @throws IOException          Ошибка чтения
     */
    private Stream<String> getStreamFromResourcesFile(String filename) throws NullPointerException, IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) {
            throw new IOException("Requested file was not found!");
        }
        Path p = Paths.get(resource.toURI());
        return Files.lines(p, StandardCharsets.UTF_8);
    }
}
