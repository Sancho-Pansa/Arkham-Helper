package io.sanchopansa.arkham;

import io.sanchopansa.arkham.cards.CommonItem;
import io.sanchopansa.arkham.factories.AbstractGameFactory;
import io.sanchopansa.arkham.factories.JsonGameFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;

public class JsonGameFactoryTest {
    @Test
    public void FactoryThrowTest() {
        AbstractGameFactory factory = new JsonGameFactory();
        Assertions.assertDoesNotThrow(factory::createVault);
    }

    @Test
    public void cloningProcessTest() throws Exception {
        AbstractGameFactory factory = new JsonGameFactory();
        var vault = factory.createVault();
        LinkedList<CommonItem> commons = vault.getCommonItems();
        ArrayList<CommonItem> eponymousItems = new ArrayList<>(commons.stream().filter(item -> item.getName().equals("Динамит")).toList());
        Assertions.assertNotSame(eponymousItems.get(0), eponymousItems.get(1));
    }

    @Test
    public void listInvestigators() throws IOException, URISyntaxException {
        GameVault vault = new JsonGameFactory().createVault();
        var investigators = vault.getInvestigators();
        investigators.forEach(i -> System.out.println(i.getName()));
        Assertions.assertFalse(investigators.isEmpty());
    }
}