package io.sanchopansa.arkham;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import io.sanchopansa.arkham.cards.AbstractCard;
import io.sanchopansa.arkham.deserializers.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.Ancient;
import io.sanchopansa.arkham.monsters.Gate;
import io.sanchopansa.arkham.monsters.Monster;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonExtractor {
    public <T extends AbstractCard> Set<T> extractCardsSetByType(String filename,
                                                                 java.lang.reflect.Type type,
                                                                 java.lang.reflect.Type arrayType,
                                                                 JsonDeserializer<T> deserializer) throws IOException, URISyntaxException {
        matchFilename(filename);

        String json = getStreamFromResourcesFile(filename).collect(Collectors.joining());
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(type, deserializer);
        return Set.of(gBuilder.create().fromJson(json, arrayType));
    }

    public Set<Investigator> extractInvestigatorsFromJson(String filename) throws IOException, URISyntaxException {
        matchFilename(filename);
        String json = getStreamFromResourcesFile(filename).collect(Collectors.joining());
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(Investigator.class, new InvestigatorDeserializer());
        return new HashSet<>(Arrays.asList(gBuilder.create().fromJson(json, Investigator[].class)));
    }

    public Set<Ancient> extractAncientsFromJson(String filename,
                                                Set<Monster> monsters) throws IOException, URISyntaxException {
        matchFilename(filename);
        String json = getStreamFromResourcesFile(filename).collect(Collectors.joining());
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(Ancient.class, new AncientDeserializer(monsters));
        return new HashSet<>(Arrays.asList(gBuilder.create().fromJson(json, Ancient[].class)));
    }

    public List<Monster> extractMonstersFromJson(String filename) throws IOException, URISyntaxException {
        matchFilename(filename);
        String json = getStreamFromResourcesFile(filename).collect(Collectors.joining());
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(Monster.class, new MonsterDeserializer());
        return Arrays.asList(gBuilder.create().fromJson(json, Monster[].class));
    }

    public List<Gate> extractGatesFromJson(String filename) throws IOException, URISyntaxException {
        matchFilename(filename);
        String json = getStreamFromResourcesFile(filename).collect(Collectors.joining());
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(Gate.class, new GateDeserializer());
        return Arrays.asList(gBuilder.create().fromJson(json, Gate[].class));
    }

    public Map<String, Integer> extractCardCountMap(String filename,
                                                    java.lang.reflect.Type type) throws IOException, URISyntaxException {
        matchFilename(filename);

        String json = getStreamFromResourcesFile(filename).collect(Collectors.joining());
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(type, new CardCountDeserializer());
        return gBuilder.create().fromJson(json, type);
    }

    public Map<String, Location> extractLocationsAsMap(String filename) {
        matchFilename(filename);
        Map<String, Location> locationMap = new HashMap<>();

        try {
            GsonBuilder gBuilder = new GsonBuilder();
            var locationJson = getStreamFromResourcesFile(filename).collect(Collectors.joining());
            Type mapTypeToken = new TypeToken<Map<String, Location>>() {
            }.getType();
            gBuilder.registerTypeAdapter(mapTypeToken, new LocationDeserializer());
            Gson gson = gBuilder.create();

            JsonElement nodesJson = gson
                    .fromJson(locationJson, JsonElement.class)
                    .getAsJsonObject().get("graph").getAsJsonObject().get("nodes");

            locationMap = gson.fromJson(nodesJson, mapTypeToken);
        } catch(IOException e) {
            System.err.printf("Error during %s processing!%n", filename);
            e.printStackTrace(System.err);
        } catch(URISyntaxException e) {
            System.err.println("Error in URI conversion (Java NIO)!");
            e.printStackTrace(System.err);
        }

        return locationMap;
    }

    public List<ImmutablePair<String, String>> extractEdgesAsPairs(String filename) {
        matchFilename(filename);

        List<ImmutablePair<String, String>> edgesPairs = new ArrayList<>();
        try {
            GsonBuilder gBuilder = new GsonBuilder();
            var locationJson = getStreamFromResourcesFile(filename).collect(Collectors.joining());
            Gson gson = gBuilder.create();

            JsonArray edgesArray = gson
                    .fromJson(locationJson, JsonElement.class)
                    .getAsJsonObject().get("graph")
                    .getAsJsonObject().get("edges")
                    .getAsJsonArray();
            for(JsonElement x : edgesArray) {
                String source = x.getAsJsonObject().get("source").getAsString();
                String target = x.getAsJsonObject().get("target").getAsString();
                edgesPairs.add(new ImmutablePair<>(source, target));
            }
        } catch(IOException e) {
            System.err.printf("Error during %s processing!%n", filename);
            e.printStackTrace(System.err);
        } catch(URISyntaxException e) {
            System.err.println("Error in URI conversion (Java NIO)!");
            e.printStackTrace(System.err);
        }

        return edgesPairs;
    }

    private void matchFilename(String filename) throws IllegalArgumentException {
        if(!filename.matches(".*\\.json")) {
            throw new IllegalArgumentException("Wrong file extension, expected .json");
        }
    }

    private Stream<String> getStreamFromResourcesFile(String filename) throws NullPointerException, IOException, URISyntaxException {
        URL resource = this.getClass().getClassLoader().getResource(filename);
        if(resource == null) {
            throw new IOException(String.format("Requested file \"%s\" was not found!", filename));
        }
        Path p = Paths.get(resource.toURI());
        return Files.lines(p, StandardCharsets.UTF_8);
    }
}
