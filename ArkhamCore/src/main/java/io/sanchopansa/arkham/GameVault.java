package io.sanchopansa.arkham;

import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.Ancient;
import io.sanchopansa.arkham.monsters.Gate;
import io.sanchopansa.arkham.monsters.Monster;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Set;

/**
 * Класс, который воплощает "коробку" - список всех карт, жетонов и аксессуаров, которые нужно подготовить к игре.
 */
public class GameVault {
    private final Set<Investigator> investigators;
    private final ArrayList<Monster> monsterPool;
    private final ArrayList<Gate> gatePool;
    private final Set<Ancient> ancientPool;

    private final Deque<CommonItem> commonItems;
    private final Deque<UniqueItem> uniqueItems;
    private final Deque<Spell> spells;
    private final Deque<SkillCard> skills;
    private final Set<Ally> allies;

    public GameVault(Set<Investigator> investigators,
                     ArrayList<Monster> monsterPool,
                     ArrayList<Gate> gatePool,
                     Set<Ancient> ancientPool,
                     Deque<CommonItem> commonItems,
                     Deque<UniqueItem> uniqueItems,
                     Deque<Spell> spells,
                     Deque<SkillCard> skills,
                     Set<Ally> allies
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

    public Deque<CommonItem> getCommonItems() {
        return commonItems;
    }

    public Deque<UniqueItem> getUniqueItems() {
        return uniqueItems;
    }

    public Deque<Spell> getSpells() {
        return spells;
    }

    public Deque<SkillCard> getSkills() {
        return skills;
    }

    public Set<Ally> getAllies() {
        return allies;
    }
}
