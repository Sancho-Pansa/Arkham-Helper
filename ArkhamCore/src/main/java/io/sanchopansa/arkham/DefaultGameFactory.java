package io.sanchopansa.arkham;

import com.google.gson.JsonDeserializer;
import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.deserializers.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.Ancient;
import io.sanchopansa.arkham.monsters.Gate;
import io.sanchopansa.arkham.monsters.Monster;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Сборщик коробки по умолчанию - из заранее составленных JSON-файлов.
 */
public class DefaultGameFactory extends AbstractGameFactory {
    @Override
    public GameVault createVault() {
        JsonExtractor jsonExtractor = new JsonExtractor();
        try {
            // Таблица "Объект игры - число объектов"
            Map<String, Integer> objectCountMap = new HashMap<>();
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("CommonItems.json", CommonItem.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("UniqueItems.json", UniqueItem.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("Spells.json", Spell.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("Skills.json", SkillCard.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("Allies.json", Ally.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("Monsters.json", Monster.class));
            objectCountMap.putAll(jsonExtractor.extractCardCountMap("Gates.json", Gate.class));

            // Создать колоды карт (с повторяющимися элементами)
            LinkedList<CommonItem> commonsDeck = createDeckFromCardSet(
                    getSetFromJson("CommonItems.json", CommonItem.class, CommonItem[].class, new CommonItemDeserializer())
                            .stream()
                            .map(c -> (CommonItem) c).collect(Collectors.toSet()),
                    objectCountMap
            );

            LinkedList<UniqueItem> uniquesDeck = createDeckFromCardSet(
                    getSetFromJson("UniqueItems.json", UniqueItem.class, UniqueItem[].class, new UniqueItemDeserializer())
                            .stream()
                            .map(c -> (UniqueItem) c).collect(Collectors.toSet()),
                    objectCountMap
            );
            LinkedList<Spell> spellsDeck = createDeckFromCardSet(
                    getSetFromJson("Spells.json", Spell.class, Spell[].class, new SpellDeserializer())
                            .stream()
                            .map(c -> (Spell) c).collect(Collectors.toSet()),
                    objectCountMap
            );
            LinkedList<SkillCard> skillsDeck = createDeckFromCardSet(
                    getSetFromJson("Skills.json", SkillCard.class, SkillCard[].class, new SkillDeserializer())
                            .stream()
                            .map(c -> (SkillCard) c).collect(Collectors.toSet()),
                    objectCountMap
            );
            LinkedList<Ally> alliesDeck = createDeckFromCardSet(
                    getSetFromJson("Allies.json", Ally.class, Ally[].class, new AllyDeserializer())
                            .stream()
                            .map(c -> (Ally) c).collect(Collectors.toSet()),
                    objectCountMap
            );

            // Монстры
            ArrayList<Monster> monstersPool = getMonstersListFromJson(objectCountMap);

            // Врата
            ArrayList<Gate> gatePool = getGatesFromJson(objectCountMap);

            //Древние
            Set<Ancient> ancients = new JsonExtractor().extractAncientsFromJson("Ancients.json", new HashSet<>(monstersPool));

            // Сыщики
            Set<Investigator> investigators = jsonExtractor.extractInvestigatorsFromJson("Investigators.json");

            return new GameVault(
                    investigators,
                    monstersPool,
                    gatePool,
                    ancients,
                    commonsDeck,
                    uniquesDeck,
                    spellsDeck,
                    skillsDeck,
                    alliesDeck
            );

        } catch (IOException e) {
            System.err.println("Error during file reading process!");
            e.printStackTrace(System.err);
        } catch (URISyntaxException e) {
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

    private ArrayList<Monster> getMonstersListFromJson(Map<String, Integer> cardCountMap) throws IOException, URISyntaxException {
        return new JsonExtractor().extractMonstersFromJson("Monsters.json").stream()
                .collect(
                        ArrayList::new,
                        // l - list
                        (l, monster) -> {
                            l.add(monster);
                            try {
                                if (cardCountMap.containsKey(monster.getName())) {
                                    for (int i = 1; i < cardCountMap.get(monster.getName()); i++) {
                                        l.add(monster.clone());
                                    }
                                }
                            } catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        ArrayList::addAll
                );
    }

    private ArrayList<Gate> getGatesFromJson(Map<String, Integer> cardCountMap) throws IOException, URISyntaxException {
        return new JsonExtractor().extractGatesFromJson("Gates.json").stream()
                .collect(
                        ArrayList::new,
                        // l - list
                        (l, gate) -> {
                            l.add(gate);
                            try {
                                if (cardCountMap.containsKey(gate.getWorld())) {
                                    for (int i = 1; i < cardCountMap.get(gate.getWorld()); i++) {
                                        l.add(gate.clone());
                                    }
                                }
                            } catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        ArrayList::addAll
                );
    }

    /**
     * Преобразует поданный Сет в Список в соответствии с переданной таблицей соответствий карт и их количества.
     *
     * @param set          Сет карт
     * @param cardCountMap Таблица вида "Карта" - "Количество этих карт"
     * @return Двунаправленный Список из карт (не перемешанный)
     */
    @SuppressWarnings("unchecked")
    private <T extends AbstractCard> LinkedList<T> createDeckFromCardSet(Set<T> set, Map<String, Integer> cardCountMap) {
        return set.stream()
                .collect(
                        LinkedList::new,
                        // ll - linked_list
                        (ll, item) -> {
                            ll.add(item);
                            try {
                                if (cardCountMap.containsKey(item.getName())) {
                                    for (int i = 1; i < cardCountMap.get(item.getName()); i++) {
                                        ll.add((T) item.clone());
                                    }
                                }
                            } catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        LinkedList::addAll
                );
    }

    private <T extends AbstractCard> Queue<T> shuffleDeck(Queue<T> deck) {
        var linkedDeck = new LinkedList<>(deck);
        Collections.shuffle(linkedDeck);
        Collections.shuffle(linkedDeck);
        return linkedDeck;
    }
}
