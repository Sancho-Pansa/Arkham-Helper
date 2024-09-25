package io.sanchopansa.arkham;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import io.sanchopansa.arkham.cards.AbstractCard;
import io.sanchopansa.arkham.deserializers.InvestigatorDeserializer;
import io.sanchopansa.arkham.investigators.Investigator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonExtractor {
    public List<? extends AbstractCard> extractCardsFromJson(String filename, java.lang.reflect.Type type, java.lang.reflect.Type arrayType, JsonDeserializer<? extends AbstractCard> deserializer) throws IOException, URISyntaxException {
        matchFilename(filename);

        String json = getStreamFromResourcesFile(filename).collect(Collectors.joining());
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(type, deserializer);
        return Arrays.asList(gBuilder.create().fromJson(json, arrayType));
    }

    public Collection<Investigator> extractInvestigatorsFromJson(String filename) throws IOException, URISyntaxException {
        matchFilename(filename);
        String json = getStreamFromResourcesFile(filename).collect(Collectors.joining());
        GsonBuilder gBuilder = new GsonBuilder();
        gBuilder.registerTypeAdapter(Investigator.class, new InvestigatorDeserializer());
        return Arrays.asList(gBuilder.create().fromJson(json, Investigator[].class));
    }

    private void matchFilename(String filename) throws IllegalArgumentException {
        if(!filename.matches(".*\\.json")) {
            throw new IllegalArgumentException("Wrong file extension, expected .json");
        }
    }

    private Stream<String> getStreamFromResourcesFile(String filename) throws NullPointerException, IOException, URISyntaxException {
        URL resource = this.getClass().getClassLoader().getResource(filename);
        if (resource == null) {
            throw new IOException("Requested file was not found!");
        }
        Path p = Paths.get(resource.toURI());
        return Files.lines(p, StandardCharsets.UTF_8);
    }
}