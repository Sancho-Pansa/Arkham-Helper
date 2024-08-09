package io.sanchopansa.arkham;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameFactory {
    // TODO: Файлы для хранилища
    private /*final*/ GameVault gameVault;
    //private Game gameInstance;
    private final static Path RESOURCES_DIR = Paths.get("resources/1.json");

    // TODO: Определить относительное положение ресурсов
    public GameFactory() {
        try {
            System.out.println(this.getClass().getResource("/"));
            String a = Files.readString(RESOURCES_DIR);
            System.out.println(a);
        } catch(IOException e) {

        }
    }

    public GameVault createVault() {
        GameVaultBuilder vaultBuilder = new GameVaultBuilder();

        //vaultBuilder.createDefaultLayout();
        return null;
    }
}
