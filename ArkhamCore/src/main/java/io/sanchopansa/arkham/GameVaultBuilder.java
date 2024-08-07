package io.sanchopansa.arkham;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.MutableGraph;
import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.*;
import java.util.ArrayList;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public class GameVaultBuilder implements GameVaultBuilderInterface {
    private MutableGraph<Location> map;
    private Set<AncientOne> ancients;
    private Set<Investigator> investigators;

    private ArrayList<CommonItem> commonItems;
    private ArrayList<UniqueItem> uniqueItems;
    private ArrayList<Spell> spells;
    private ArrayList<SkillCard> skills;
    private Set<Ally> allies;

    private ArrayList<Monster> monsters;
    private ArrayList<Gate> gates;

    @Override
    public GameVaultBuilder createDefaultLayout(MutableGraph<Location> map,
                                                Set<AncientOne> ancients,
                                                Set<Investigator> investigators,
                                                ArrayList<CommonItem> commonItems,
                                                ArrayList<UniqueItem> uniqueItems,
                                                ArrayList<Spell> spell,
                                                ArrayList<SkillCard> skills,
                                                Set<Ally> allies,
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
    public GameVaultBuilder addAncients(Set<AncientOne> ancients) {
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
                map,
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
