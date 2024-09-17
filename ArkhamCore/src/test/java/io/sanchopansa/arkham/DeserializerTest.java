package io.sanchopansa.arkham;

import com.google.gson.GsonBuilder;
import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.deserializers.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DeserializerTest {
    static GsonBuilder gBuilder;
    static CommonItem[] commonItems;
    static Monster[] monsters;

    @BeforeAll
    public static void setUpBeforeClass() throws IOException, URISyntaxException {
        gBuilder = new GsonBuilder();
    }
    
    @AfterEach
    public void tearDown() {
        gBuilder = new GsonBuilder();
    }

    @Test
    public void InvestigatorDeserializationTest() throws IOException, URISyntaxException {
        var investigatorCollection = new DeserializerTest()
                .getStreamFromResourcesFile("Investigators.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(Investigator.class, new InvestigatorDeserializer());
        var investigators = gBuilder.create().fromJson(investigatorCollection, Investigator[].class);
        assertTrue(investigators.length > 0, "Array of investigators is not empty");
    }

    @Test
    public void CommonItemDeserializationTest() throws IOException, URISyntaxException {
        var commonItemsJson = new DeserializerTest()
                .getStreamFromResourcesFile("CommonItems.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(CommonItem.class, new CommonItemDeserializer());
        var commonItems = gBuilder.create().fromJson(commonItemsJson, CommonItem[].class);
        assertTrue(commonItems.length > 0, "Array of Common Items is not empty");
    }

    @Test
    public void MonsterDeserializationTest() throws IOException, URISyntaxException {
        var monsterCollection = new DeserializerTest()
                .getStreamFromResourcesFile("Monsters.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(Monster.class, new MonsterDeserializer());
        var monsters = gBuilder.create().fromJson(monsterCollection, Monster[].class);

        assertTrue(monsters.length > 0);
        System.out.println(monsters[0].toString());
        assertTrue(Arrays.stream(monsters).anyMatch((monster -> monster.getName().equals("Культист"))));
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
