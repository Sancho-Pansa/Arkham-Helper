package io.sanchopansa.arkham;

import com.google.common.graph.MutableGraph;
import io.sanchopansa.arkham.cards.AbstractCard;
import io.sanchopansa.arkham.cards.CommonItem;
import io.sanchopansa.arkham.deserializers.CommonItemDeserializer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class DefaultGameFactory extends AbstractGameFactory {
    @Override
    public GameVault createVault() {
        JsonExtractor jsonExtractor = new JsonExtractor();
        try {
            List<? extends AbstractCard> a = jsonExtractor.extractCardsFromJson("CommonItems.json", CommonItem.class, CommonItem[].class, new CommonItemDeserializer());
            Queue<CommonItem> commons = a.stream().map(e -> (CommonItem) e).collect(Collectors.toCollection(ArrayDeque::new));
            System.out.println(commons.size());
        } catch(IOException e) {
            System.err.println("Error during file reading process!");
        } catch(URISyntaxException e) {
            System.err.println("Error in URI conversion (Java NIO)!");
        }
        GameVaultBuilder vaultBuilder = new GameVaultBuilder();

        return null;
    }

    @Override
    public MutableGraph<Location> createMap() {
        return null;
    }
}
