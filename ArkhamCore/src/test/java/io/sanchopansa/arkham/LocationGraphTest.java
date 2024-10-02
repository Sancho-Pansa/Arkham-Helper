package io.sanchopansa.arkham;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import io.sanchopansa.arkham.deserializers.LocationDeserializer;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class LocationGraphTest {
    private static Map<String, Location> locationMap;
    private static final List<ImmutablePair<Location, Location>> edgesPairs = new ArrayList<>();
    private static Graph<Location, DefaultEdge> graph;

    @BeforeAll
    public static void getLocations() throws IOException {
        URL resource = LocationGraphTest.class.getClassLoader().getResource("ArkhamMap.json");
        if(resource == null) {
            throw new IOException("Requested file was not found!");
        }
        try(Stream<String> jsonFile = Files
                .lines(Paths.get(resource.toURI()), StandardCharsets.UTF_8)) {
            String locationJson = jsonFile.collect(Collectors.joining());

            Type mapTypeToken = new TypeToken<Map<String, Location>>() {
            }.getType();
            GsonBuilder gBuilder = new GsonBuilder();
            gBuilder.registerTypeAdapter(mapTypeToken, new LocationDeserializer());
            Gson gson = gBuilder.create();

            JsonElement a = gson.fromJson(locationJson, JsonElement.class);
            JsonElement nodesJson = a.getAsJsonObject().get("graph").getAsJsonObject().get("nodes");

            locationMap = gson.fromJson(nodesJson, mapTypeToken);
            JsonElement edgesJson = a.getAsJsonObject().get("graph").getAsJsonObject().get("edges");
            var edgesArray = edgesJson.getAsJsonArray();

            for(JsonElement x : edgesArray) {
                String source = x.getAsJsonObject().get("source").getAsString();
                String target = x.getAsJsonObject().get("target").getAsString();
                edgesPairs.add(new ImmutablePair<>(locationMap.get(source), locationMap.get(target)));
            }

        } catch(IOException | URISyntaxException e) {
            System.err.println("IOException during Json process!");
            e.printStackTrace(System.err);
        }
    }

    @BeforeEach
    public void createGraph() {
        graph = new SimpleGraph<>(DefaultEdge.class);
        locationMap.values().forEach(graph::addVertex);
        edgesPairs.forEach(pair -> graph.addEdge(pair.left, pair.right));
    }

    @Test
    public void testGraphNonNullity() {
        assertNotNull(graph);
    }

    @Test
    public void testGraphConnection() {
        assertEquals(0, graph.vertexSet().stream().filter(l -> graph.degreeOf(l) < 1).count());
    }

    @Test
    public void testDijkstraAlgorithm() {
        Location src = locationMap.get("Independence Square");
        Location dst = locationMap.get("River Docks");
        DijkstraShortestPath<Location, DefaultEdge> dj = new DijkstraShortestPath<>(graph);
        GraphPath<Location, DefaultEdge> shortestPath = dj.getPath(src, dst);
        assertEquals(3, shortestPath.getLength());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 5 })
    public void testBFS(int range) {
        Location src = locationMap.get("South Church");
        BreadthFirstIterator<Location, DefaultEdge> bfsIter = new BreadthFirstIterator<>(graph, src);
        Set<Location> locationsInRange = new HashSet<>();
        do {
            Location current = bfsIter.next();
            if(bfsIter.getDepth(current) > range)
                break;
            locationsInRange.add(current);
        } while(bfsIter.hasNext());
        locationsInRange.forEach(l -> System.out.println(l.getName()));
        assertFalse(locationsInRange.isEmpty());
    }
}
