package io.sanchopansa.arkham;

import com.google.common.graph.MutableGraph;
import com.google.gson.JsonDeserializer;
import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.deserializers.*;
import io.sanchopansa.arkham.monsters.Monster;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.*;

public class DefaultGameFactory extends AbstractGameFactory {
    @Override
    public GameVault createVault() {
        JsonExtractor jsonExtractor = new JsonExtractor();
        try {
            Map<String, Integer> objectCountMap = new HashMap<>();
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("CommonItems.json", CommonItem.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("UniqueItems.json", UniqueItem.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("Spells.json", Spell.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("Skills.json", SkillCard.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("Allies.json", Ally.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("Monsters.json", Monster.class));

            // Создать колоды карт (с повторяющимися элементами)
            LinkedList<AbstractCard> commonsDeck = createDeckFromCardSet(
                    getSetFromJson("CommonItems.json", CommonItem.class, CommonItem[].class, new CommonItemDeserializer()),
                    objectCountMap
            );

            LinkedList<AbstractCard> uniquesDeck = createDeckFromCardSet(
                    getSetFromJson("UniqueItems.json", UniqueItem.class, UniqueItem[].class, new UniqueItemDeserializer()),
                    objectCountMap
            );
            LinkedList<AbstractCard> spellsDeck = createDeckFromCardSet(
                    getSetFromJson("Spells.json", Spell.class, Spell[].class, new SpellDeserializer()),
                    objectCountMap
            );
            LinkedList<AbstractCard> skillsDeck = createDeckFromCardSet(
                    getSetFromJson("Skills.json", SkillCard.class, SkillCard[].class, new SkillDeserializer()),
                    objectCountMap
            );
            LinkedList<AbstractCard> alliesDeck = createDeckFromCardSet(
                    getSetFromJson("Allies.json", Ally.class, Ally[].class, new AllyDeserializer()),
                    objectCountMap
            );

            List<Monster> monstersBag = getMonstersListFromJson("Monsters.json", objectCountMap);

            // TODO: Мешок Врат
        } catch(IOException e) {
            System.err.println("Error during file reading process!");
            e.printStackTrace(System.err);
        } catch(URISyntaxException e) {
            System.err.println("Error in URI conversion (Java NIO)!");
            e.printStackTrace(System.err);
        }
        return null;
    }

    /**
     * Эта функция принимает на вход название JSON-а, Java-тип для его преобразования и класс-десериализатор, после
     * обработки возвращая заполненный Сет.
     * Используется для сокращения набора одних и тех же функций для каждого типа малых карт.
     *
     * @param filename      Имя файла с расширением (относительно директории resources/)
     * @param itemType      Класс, в который производится десериализация
     * @param arrayItemType Класс - массив классов, в которые производится десериализация
     * @param deserializer  Класс-десериализатор
     * @return Сет экземпляров класса
     */
    private Set<AbstractCard> getSetFromJson(String filename,
                                             Type itemType,
                                             Type arrayItemType,
                                             JsonDeserializer<? extends AbstractCard> deserializer
    ) throws IOException, URISyntaxException {
        JsonExtractor jsonExtractor = new JsonExtractor();
        return new HashSet<>(jsonExtractor.extractCardsSetByType(filename, itemType, arrayItemType, deserializer));
    }

    private List<Monster> getMonstersListFromJson(String filename,
                                                  Map<String, Integer> cardCountMap
    ) throws IOException, URISyntaxException {
        JsonExtractor jsonExtractor = new JsonExtractor();
        return jsonExtractor.extractMonstersFromJson(filename).stream()
                .collect(
                        ArrayList::new,
                        // l - list
                        (l, monster) -> {
                            l.add(monster);
                            try {
                                if(cardCountMap.containsKey(monster.getName())) {
                                    for(int i = 1; i < cardCountMap.get(monster.getName()); i++) {

                                        l.add(monster.clone());
                                    }
                                }
                            } catch(CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        ArrayList::addAll
                );
    }

    /**
     * Преобразует поданный Сет в Очередь в соответствии с переданной таблицей соответствий карт и их количества.
     *
     * @param set          Сет карт
     * @param cardCountMap Таблица вида "Карта" - "Количество этих карт"
     * @return Очередь из карт (не перемешанная)
     */
    private LinkedList<AbstractCard> createDeckFromCardSet(Set<AbstractCard> set, Map<String, Integer> cardCountMap) {
        return set.stream()
                .collect(
                        LinkedList::new,
                        // ll - linked_list
                        (ll, item) -> {
                            ll.add(item);
                            try {
                                if(cardCountMap.containsKey(item.getName())) {
                                    for(int i = 1; i < cardCountMap.get(item.getName()); i++) {

                                        ll.add(item.clone());
                                    }
                                }
                            } catch(CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        LinkedList::addAll
                );
    }

    private List<Monster> createBagFromCardSet(Set<Monster> set, Map<String, Integer> cardCountMap) {
        return set.stream()
                .collect(
                        ArrayList::new,
                        // l - list
                        (l, monster) -> {
                            l.add(monster);
                            try {
                                if(cardCountMap.containsKey(monster.getName())) {
                                    for(int i = 1; i < cardCountMap.get(monster.getName()); i++) {

                                        l.add(monster.clone());
                                    }
                                }
                            } catch(CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        ArrayList::addAll
                );
    }

    private <T extends AbstractCard> Queue<T> shuffleDeck(Queue<T> deck) {
        var linkedDeck = new LinkedList<>(deck);
        Collections.shuffle(linkedDeck);
        Collections.shuffle(linkedDeck);
        return linkedDeck;
    }

    @Override
    public MutableGraph<Location> createMap() {
        return null;
    }
}
