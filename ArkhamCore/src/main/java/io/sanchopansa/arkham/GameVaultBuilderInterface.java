package io.sanchopansa.arkham;

import com.google.common.graph.MutableGraph;
import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.locations.Location;
import io.sanchopansa.arkham.monsters.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

/**
 * Интерфейс сборщика коробки
 */
@SuppressWarnings("UnstableApiUsage")
public interface GameVaultBuilderInterface {
    /**
     * Создает стандартную сборку коробки игры (Аркхэм без дополнений)
     *
     * @param map           Карта Аркхэма
     * @param ancients      Ванильные Древние
     * @param investigators Ванильные Сыщики
     * @param commonItems   Ванильные предметы
     * @param uniqueItems   Ванильные уникальные
     * @param spell         Ванильные заклинания
     * @param skills        Ванильные навыки
     * @param allies        Ванильные союзники
     * @return Строитель коробки
     */
    GameVaultBuilder createDefaultLayout(MutableGraph<Location> map,
                                         Set<Ancient> ancients,
                                         Set<Investigator> investigators,
                                         LinkedList<CommonItem> commonItems,
                                         LinkedList<UniqueItem> uniqueItems,
                                         LinkedList<Spell> spell,
                                         LinkedList<SkillCard> skills,
                                         LinkedList<Ally> allies,
                                         ArrayList<Monster> monsters,
                                         ArrayList<Gate> gates);

    /**
     * Добавить карту к игре с указанием точки присоединения
     *
     * @param map                   Новая карта
     * @param originalJointLocation Точка соединения оригинальной карты
     * @param foreignJoinLocation   Точка соединения новой карты
     * @return Строитель коробки
     */
    GameVaultBuilder addMap(MutableGraph<Location> map, Location originalJointLocation, Location foreignJoinLocation);

    GameVaultBuilder addInvestigators(Set<Investigator> investigators);

    GameVaultBuilder addAncients(Set<Ancient> ancients);

    GameVaultBuilder addCommonItems(ArrayList<CommonItem> commonItems);

    GameVaultBuilder addUniqueItems(ArrayList<UniqueItem> uniqueItems);

    GameVaultBuilder addSpells(ArrayList<Spell> spells);

    GameVaultBuilder addSkills(ArrayList<SkillCard> skills);

    GameVaultBuilder addAllies(Set<Ally> allies);

    GameVaultBuilder addMonsters(ArrayList<Monster> monsters);

    GameVaultBuilder addGates(ArrayList<Gate> gates);

    /**
     * Преобразовать сборщика в экземпляр коробки игры
     *
     * @return
     */
    GameVault build();
}
