package io.sanchopansa.arkham;

import com.google.common.graph.MutableGraph;
import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.Ancient;
import io.sanchopansa.arkham.monsters.Gate;
import io.sanchopansa.arkham.monsters.Monster;

import java.util.ArrayList;
import java.util.Set;

/**
 * Класс, который воплощает "коробку" - список всех карт, жетонов и аксессуаров, которые нужно подготовить к игре.
 */
@SuppressWarnings("UnstableApiUsage")
public class GameVault {
    private final Set<Investigator> investigators;
    private final MutableGraph<Location> gameMap;
    private final ArrayList<Monster> monsterPool;
    private final ArrayList<Gate> gatePool;
    private final Set<Ancient> ancientPool;

    private final ArrayList<CommonItem> commonItems;
    private final ArrayList<UniqueItem> uniqueItems;
    private final ArrayList<Spell> spells;
    private final ArrayList<SkillCard> skills;
    private final Set<Ally> allies;

    public GameVault(Set<Investigator> investigators,
                     MutableGraph<Location> gameMap,
                     ArrayList<Monster> monsterPool,
                     ArrayList<Gate> gatePool,
                     Set<Ancient> ancientPool,
                     ArrayList<CommonItem> commonItems,
                     ArrayList<UniqueItem> uniqueItems,
                     ArrayList<Spell> spells,
                     ArrayList<SkillCard> skills,
                     Set<Ally> allies
    ) {
        this.investigators = investigators;
        this.gameMap = gameMap;
        this.monsterPool = monsterPool;
        this.gatePool = gatePool;
        this.ancientPool = ancientPool;
        this.commonItems = commonItems;
        this.uniqueItems = uniqueItems;
        this.spells = spells;
        this.skills = skills;
        this.allies = allies;
    }

    public Set<Investigator> getInvestigators() {
        return investigators;
    }

    public MutableGraph<Location> getGameMap() {
        return gameMap;
    }

    public ArrayList<Monster> getMonsterPool() {
        return monsterPool;
    }

    public ArrayList<Gate> getGatePool() {
        return gatePool;
    }

    public Set<Ancient> getAncientOnePool() {
        return ancientPool;
    }

    public ArrayList<CommonItem> getCommonItems() {
        return commonItems;
    }

    public ArrayList<UniqueItem> getUniqueItems() {
        return uniqueItems;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public ArrayList<SkillCard> getSkills() {
        return skills;
    }

    public Set<Ally> getAllies() {
        return allies;
    }
}
