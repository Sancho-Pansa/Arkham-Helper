package io.sanchopansa.arkham.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.sanchopansa.arkham.api.JsonSource;
import io.sanchopansa.arkham.core.cards.AbstractCard;
import io.sanchopansa.arkham.core.investigators.Investigator;
import io.sanchopansa.arkham.core.locations.Location;
import io.sanchopansa.arkham.core.monsters.Ancient;
import io.sanchopansa.arkham.core.monsters.Gate;
import io.sanchopansa.arkham.core.monsters.Monster;
import io.sanchopansa.arkham.json.deserializers.AncientDeserializer;
import io.sanchopansa.arkham.json.deserializers.CardCountDeserializer;
import io.sanchopansa.arkham.json.deserializers.GateDeserializer;
import io.sanchopansa.arkham.json.deserializers.InvestigatorDeserializer;
import io.sanchopansa.arkham.json.deserializers.LocationDeserializer;
import io.sanchopansa.arkham.json.deserializers.MonsterDeserializer;

public class JsonExtractor {

    private final JsonSource source;
    private final GsonBuilder gBuilder = new GsonBuilder();

    public JsonExtractor(JsonSource source) {
        this.source = source;
    }

    public <T extends AbstractCard> Set<T> extractCardsSetByType(String filename,
                                                                 Type type,
                                                                 Type arrayType,
                                                                 JsonDeserializer<T> deserializer) throws IOException {

        try (
                InputStream is = source.openGameData(filename);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        ) {
            gBuilder.registerTypeAdapter(type, deserializer);
            return Set.of(gBuilder.create().fromJson(reader, arrayType));
        }
    }

    public Set<Investigator> extractInvestigatorsFromJson(String filename) throws IOException {
        try (
                InputStream is = source.openGameData(filename);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        ) {
            gBuilder.registerTypeAdapter(Investigator.class, new InvestigatorDeserializer());
            return new HashSet<>(Arrays.asList(gBuilder.create().fromJson(reader, Investigator[].class)));
        }
    }

    public Set<Ancient> extractAncientsFromJson(String filename,
                                                Set<Monster> monsters) throws IOException {
        try (
                InputStream is = source.openGameData(filename);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        ) {
            gBuilder.registerTypeAdapter(Ancient.class, new AncientDeserializer(monsters));
            return new HashSet<>(Arrays.asList(gBuilder.create().fromJson(reader, Ancient[].class)));
        }
    }

    public List<Monster> extractMonstersFromJson(String filename) throws IOException {
        try (
                InputStream is = source.openGameData(filename);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        ) {
            gBuilder.registerTypeAdapter(Monster.class, new MonsterDeserializer());
            return Arrays.asList(gBuilder.create().fromJson(reader, Monster[].class));
        }
    }

    public List<Gate> extractGatesFromJson(String filename) throws IOException {
        try (
                InputStream is = source.openGameData(filename);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        ) {
            gBuilder.registerTypeAdapter(Gate.class, new GateDeserializer());
            return Arrays.asList(gBuilder.create().fromJson(reader, Gate[].class));
        }
    }

    public Map<String, Integer> extractCardCountMap(String filename,
                                                    Type type) throws IOException {

        try (
                InputStream is = source.openGameData(filename);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        ) {
            gBuilder.registerTypeAdapter(type, new CardCountDeserializer());
            return gBuilder.create().fromJson(reader, type);
        }
    }

    public Map<String, Location> extractLocationsAsMap(String filename) throws IOException {
        try (
                InputStream is = source.openGameData(filename);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        ) {
            Type mapTypeToken = new TypeToken<Map<String, Location>>() {
            }.getType();
            gBuilder.registerTypeAdapter(mapTypeToken, new LocationDeserializer());
            Gson gson = gBuilder.create();

            JsonElement nodesJson = gson
                    .fromJson(reader, JsonElement.class)
                    .getAsJsonObject().get("graph").getAsJsonObject().get("nodes");

            return gson.fromJson(nodesJson, mapTypeToken);
        }
    }

    public List<ImmutablePair<String, String>> extractEdgesAsPairs(String filename) throws IOException {
        try (
                InputStream is = source.openGameData(filename);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)
        ) {
            List<ImmutablePair<String, String>> edgesPairs = new ArrayList<>();
            Gson gson = gBuilder.create();

            JsonArray edgesArray = gson
                    .fromJson(reader, JsonElement.class)
                    .getAsJsonObject().get("graph")
                    .getAsJsonObject().get("edges")
                    .getAsJsonArray();
            for (JsonElement x : edgesArray) {
                String source = x.getAsJsonObject().get("source").getAsString();
                String target = x.getAsJsonObject().get("target").getAsString();
                edgesPairs.add(new ImmutablePair<>(source, target));
            }

            return edgesPairs;
        }
    }
}
