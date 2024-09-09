package io.sanchopansa.arkham;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class GameFactory {
    private final GameVault gameVault = null;
    private final Game gameInstance = null;


    public GameVault createVault() {
        GameVaultBuilder vaultBuilder = new GameVaultBuilder();

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
    private Stream<String> getStreamFromResourcesFile(String filename) throws NullPointerException, IOException {
        URL resource = getClass().getClassLoader().getResource(filename);
        if(resource == null) {
            throw new NullPointerException("Requested file was not found!");
        }
        return Files.lines(Paths.get(resource.getPath()), StandardCharsets.UTF_8);
    }
}
