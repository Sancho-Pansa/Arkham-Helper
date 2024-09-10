package io.sanchopansa.arkham;

import com.google.gson.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.investigators.Skill;
import io.sanchopansa.arkham.investigators.Stat;

import java.io.IOException;
import java.lang.reflect.Type;
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
            //TypeToken<Collection<AncientOne>> typeToken = new TypeToken<>() { }; // These curly braces define an anonymous inner class.
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(Stat.class, new JsonDeserializer<>() {
                @Override
                public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    return new Stat(jsonElement.getAsInt());
                }
            });
            gson.registerTypeAdapter(Skill.class, new JsonDeserializer<>() {
                @Override
                public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    return new Skill(jsonElement.getAsInt(), jsonElement.getAsInt());
                }
            });
            Investigator[] investigators = gson.create().fromJson(stream.collect(Collectors.joining()), Investigator[].class);
            Arrays.stream(investigators).forEach(System.out::println);
            //stream.forEach(System.out::println);

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
