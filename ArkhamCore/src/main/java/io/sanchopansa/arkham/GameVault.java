package io.sanchopansa.arkham;

import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.Ancient;
import io.sanchopansa.arkham.monsters.Gate;
import io.sanchopansa.arkham.monsters.Monster;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

/**
 * Класс, который воплощает "коробку" - список всех карт, жетонов и аксессуаров, которые нужно подготовить к игре.
 */
public class GameVault {
    private final Set<Investigator> investigators;
    private final ArrayList<Monster> monsterPool;
    private final ArrayList<Gate> gatePool;
    private final Set<Ancient> ancientPool;

    private final LinkedList<CommonItem> commonItems;
    private final LinkedList<UniqueItem> uniqueItems;
    private final LinkedList<Spell> spells;
    private final LinkedList<SkillCard> skills;
    private final LinkedList<Ally> allies;

    public GameVault(Set<Investigator> investigators,
                     ArrayList<Monster> monsterPool,
                     ArrayList<Gate> gatePool,
                     Set<Ancient> ancientPool,
                     LinkedList<CommonItem> commonItems,
                     LinkedList<UniqueItem> uniqueItems,
                     LinkedList<Spell> spells,
                     LinkedList<SkillCard> skills,
                     LinkedList<Ally> allies
    ) {
        this.investigators = investigators;
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

    public ArrayList<Monster> getMonsterPool() {
        return monsterPool;
    }

    public ArrayList<Gate> getGatePool() {
        return gatePool;
    }

    public Set<Ancient> getAncientsPool() {
        return ancientPool;
    }

    public LinkedList<CommonItem> getCommonItems() {
        return commonItems;
    }

    public LinkedList<UniqueItem> getUniqueItems() {
        return uniqueItems;
    }

    public LinkedList<Spell> getSpells() {
        return spells;
    }

    public LinkedList<SkillCard> getSkills() {
        return skills;
    }

    public LinkedList<Ally> getAllies() {
        return allies;
    }
}
