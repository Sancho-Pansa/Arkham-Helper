package io.sanchopansa.arkham;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.MutableGraph;
import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.locations.Location;
import io.sanchopansa.arkham.monsters.Ancient;
import io.sanchopansa.arkham.monsters.Gate;
import io.sanchopansa.arkham.monsters.Monster;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public class GameVaultBuilder implements GameVaultBuilderInterface {
    private MutableGraph<Location> map;
    private Set<Ancient> ancients;
    private Set<Investigator> investigators;

    private LinkedList<CommonItem> commonItems;
    private LinkedList<UniqueItem> uniqueItems;
    private LinkedList<Spell> spells;
    private LinkedList<SkillCard> skills;
    private LinkedList<Ally> allies;

    private ArrayList<Monster> monsters;
    private ArrayList<Gate> gates;

    @Override
    public GameVaultBuilder createDefaultLayout(MutableGraph<Location> map,
                                                Set<Ancient> ancients,
                                                Set<Investigator> investigators,
                                                LinkedList<CommonItem> commonItems,
                                                LinkedList<UniqueItem> uniqueItems,
                                                LinkedList<Spell> spell,
                                                LinkedList<SkillCard> skills,
                                                LinkedList<Ally> allies,
                                                ArrayList<Monster> monsters,
                                                ArrayList<Gate> gates) {
        this.map = map;
        this.ancients = ancients;
        this.investigators = investigators;
        this.commonItems = commonItems;
        this.uniqueItems = uniqueItems;
        this.spells = spell;
        this.skills = skills;
        this.allies = allies;
        return this;
    }

    @Override
    public GameVaultBuilder addMap(MutableGraph<Location> map, Location originalJointLocation, Location foreignJoinLocation) {
        for(Location l : map.nodes()) {
            this.map.addNode(l);
        }

        for(EndpointPair<Location> e : map.edges()) {
            this.map.putEdge(e);
        }

        this.map.putEdge(originalJointLocation, foreignJoinLocation);
        return this;
    }

    @Override
    public GameVaultBuilder addInvestigators(Set<Investigator> investigators) {
        this.investigators.addAll((investigators));
        return this;
    }

    @Override
    public GameVaultBuilder addAncients(Set<Ancient> ancients) {
        this.ancients.addAll((ancients));
        return this;
    }

    @Override
    public GameVaultBuilder addCommonItems(ArrayList<CommonItem> commonItems) {
        this.commonItems.addAll(commonItems);
        return this;
    }

    @Override
    public GameVaultBuilder addUniqueItems(ArrayList<UniqueItem> uniqueItems) {
        this.uniqueItems.addAll(uniqueItems);
        return this;
    }

    @Override
    public GameVaultBuilder addSpells(ArrayList<Spell> spells) {
        this.spells.addAll((spells));
        return this;
    }

    @Override
    public GameVaultBuilder addSkills(ArrayList<SkillCard> skills) {
        this.skills.addAll(skills);
        return this;
    }

    @Override
    public GameVaultBuilder addAllies(Set<Ally> allies) {
        this.allies.addAll(allies);
        return this;
    }

    @Override
    public GameVaultBuilder addMonsters(ArrayList<Monster> monsters) {
        this.monsters.addAll(monsters);
        return this;
    }

    @Override
    public GameVaultBuilder addGates(ArrayList<Gate> gates) {
        this.gates.addAll(gates);
        return this;
    }

    @Override
    public GameVault build() {
        return new GameVault(
                investigators,
                monsters,
                gates,
                ancients,
                commonItems,
                uniqueItems,
                spells,
                skills,
                allies);
    }
}
