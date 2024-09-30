package io.sanchopansa.arkham;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.deserializers.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.Ancient;
import io.sanchopansa.arkham.monsters.Gate;
import io.sanchopansa.arkham.monsters.Monster;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeserializerTest {
    static GsonBuilder gBuilder;

    @BeforeEach
    public void setUp() {
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
        Arrays.stream(investigators).forEach(System.out::println);
    }

    @Test
    public void CommonItemDeserializationTest() throws IOException, URISyntaxException {
        var commonItemsJson = new DeserializerTest()
                .getStreamFromResourcesFile("CommonItems.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(CommonItem.class, new CommonItemDeserializer());
        var commonItems = gBuilder.create().fromJson(commonItemsJson, CommonItem[].class);
        assertTrue(commonItems.length > 0, "Array of Common Items is not empty");
        Arrays.stream(commonItems).forEach(System.out::println);
    }

    @Test
    public void UniqueItemDeserializationTest() throws IOException, URISyntaxException {
        var uniqueItemsJson = new DeserializerTest()
                .getStreamFromResourcesFile("UniqueItems.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(UniqueItem.class, new UniqueItemDeserializer());
        var uniqueItems = gBuilder.create().fromJson(uniqueItemsJson, UniqueItem[].class);
        assertTrue(uniqueItems.length > 0, "Array of Unique Items is not empty");
        Arrays.stream(uniqueItems).forEach(System.out::println);
    }

    @Test
    public void SpellsDeserializationTest() throws IOException, URISyntaxException {
        var spellsJson = new DeserializerTest()
                .getStreamFromResourcesFile("Spells.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(Spell.class, new SpellDeserializer());
        var spells = gBuilder.create().fromJson(spellsJson, Spell[].class);
        assertTrue(spells.length > 0, "Array of Spells is not empty");
        Arrays.stream(spells).forEach(System.out::println);
    }

    @Test
    public void SkillsDeserializationTest() throws IOException, URISyntaxException {
        var skillsJson = new DeserializerTest()
                .getStreamFromResourcesFile("Skills.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(SkillCard.class, new SkillDeserializer());
        var skills = gBuilder.create().fromJson(skillsJson, SkillCard[].class);
        assertTrue(skills.length > 0, "Array of Skills is not empty");
        Arrays.stream(skills).forEach(System.out::println);
    }

    @Test
    public void AlliesDeserializationTest() throws IOException, URISyntaxException {
        var alliesJson = new DeserializerTest()
                .getStreamFromResourcesFile("Allies.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(Ally.class, new AllyDeserializer());
        var allies = gBuilder.create().fromJson(alliesJson, Ally[].class);
        assertTrue(allies.length > 0, "Array of Allies is not empty");
        Arrays.stream(allies).forEach(System.out::println);
    }

    @Test
    public void MonsterDeserializationTest() throws IOException, URISyntaxException {
        var monsterCollection = new DeserializerTest()
                .getStreamFromResourcesFile("Monsters.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(Monster.class, new MonsterDeserializer());
        var monsters = gBuilder.create().fromJson(monsterCollection, Monster[].class);
        assertTrue(monsters.length > 0);
        Arrays.stream(monsters).forEach(System.out::println);
    }

    @Test
    public void GateDeserializerTest() throws IOException, URISyntaxException {
        var monsterCollection = new DeserializerTest()
                .getStreamFromResourcesFile("Gates.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(Gate.class, new GateDeserializer());
        var gates = gBuilder.create().fromJson(monsterCollection, Gate[].class);
        assertTrue(gates.length > 0);
        Arrays.stream(gates).forEach(System.out::println);
    }

    @Test
    public void AncientDeserializerTest() throws IOException, URISyntaxException {
        var monsterCollection = new DeserializerTest()
                .getStreamFromResourcesFile("Monsters.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(Monster.class, new MonsterDeserializer());
        HashSet<Monster> monsters = new HashSet<>(Arrays.asList(gBuilder.create().fromJson(monsterCollection, Monster[].class)));

        var ancientCollection = new DeserializerTest()
                .getStreamFromResourcesFile("Ancients.json")
                .collect(Collectors.joining());
        gBuilder.registerTypeAdapter(Ancient.class, new AncientDeserializer(monsters));
        var ancients = gBuilder.create().fromJson(ancientCollection, Ancient[].class);
        assertTrue(ancients.length > 0);
        Arrays.stream(ancients).forEach(System.out::println);
    }

    @Test
    public void CountedCollectionTest() throws IOException, URISyntaxException {
        var monsterCollection = new DeserializerTest()
                .getStreamFromResourcesFile("Monsters.json")
                .collect(Collectors.joining());
        java.lang.reflect.Type type = new TypeToken<Map<String, Integer>>(){ }.getType();
        gBuilder.registerTypeAdapter(type, new CardCountDeserializer());
        Map<String, Integer> monsterCountMap = gBuilder.create().fromJson(monsterCollection, type);
        assertFalse(monsterCountMap.keySet().isEmpty());
        System.out.println(monsterCountMap);
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
