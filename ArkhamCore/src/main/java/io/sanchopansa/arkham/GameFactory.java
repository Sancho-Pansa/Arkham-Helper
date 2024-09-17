package io.sanchopansa.arkham;

import com.google.gson.*;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameFactory {
    private final GameVault gameVault = null;
    private final Game gameInstance = null;

    public GameVault createVault() {
        GameVaultBuilder vaultBuilder = new GameVaultBuilder();

        try {
            var stream = getStreamFromResourcesFile("Investigators.json");
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(Investigator.class, new InvestigatorDeserializer());
            Investigator[] investigators = gson.create().fromJson(stream.collect(Collectors.joining()), Investigator[].class);
            Arrays.stream(investigators).forEach(System.out::println);

        } catch(IOException e) {
            System.err.println("Error during file reading process!");
        } catch(URISyntaxException e) {
            System.err.println("Error in URI conversion (Java NIO)!");
        }

        //vaultBuilder.createDefaultLayout();
        return null;
    }

    /**
     *
     * @param filename Имя JSON'а (корневой директорией считается resources/)
     * @return Стрим из файла
     * @throws NullPointerException Если файла не существует
     * @throws IOException Ошибка чтения
     */
    private Stream<String> getStreamFromResourcesFile(String filename) throws NullPointerException, IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(filename);
        if(resource == null) {
            throw new IOException("Requested file was not found!");
        }
        Path p = Paths.get(resource.toURI());
        return Files.lines(p, StandardCharsets.UTF_8);
    }
}
