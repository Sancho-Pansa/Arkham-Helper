package io.sanchopansa.arkham.json;

import io.sanchopansa.arkham.mechanics.AncientOne;
import io.sanchopansa.arkham.mechanics.AncientTypes;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AncientOneJsonTest {

    AncientOneJson aojson;

    @Before
    public void setUp() {
        aojson = new AncientOneJson("resources/AncientOnesTest.json");
    }

    @Test
    public void extractCollection() {
        AncientOne ao = new AncientOne(
                "Ктулху",
                13,
                new HashSet<>(Arrays.asList(AncientTypes.INVESTIGATOR_RELATED)),
                "Культисты",
                "Сны безумия",
                "Предельные значения рассудка и здоровья сыщиков на 1 меньше обычного."
        );
        Collection<AncientOne> aoCollection = aojson.extractCollection();
        assertTrue(aoCollection.contains(ao));
    }

    @Test
    public void add() {
        Set<AncientTypes> type = new HashSet<>();
        type.add(AncientTypes.MONSTER_RELATED);
        AncientOne ao = new AncientOne(
                "Шуб-Ниггурат",
                12,
                type,
                "Темная молодь",
                "Черная Коза Чащоб",
                "Если Аркхэму угрожает Шуб-Ниггурат, стойкость всех монстров на 1 выше обычного"
        );
        aojson.add(ao);
        Set<AncientOne> aoSet = new HashSet<>(aojson.extractCollection());
        assertTrue(aoSet.contains(ao));
    }

    @Test
    public void extractAO() {
        AncientOne ao = new AncientOne(
                "Ктулху",
                13,
                new HashSet<>(Arrays.asList(AncientTypes.INVESTIGATOR_RELATED)),
                "Культисты",
                "Сны безумия",
                "Предельные значения рассудка и здоровья сыщиков на 1 меньше обычного."
        );
        AncientOne extracted = aojson.extractAO("Ктулху");
        assertEquals(ao, extracted);
    }
}