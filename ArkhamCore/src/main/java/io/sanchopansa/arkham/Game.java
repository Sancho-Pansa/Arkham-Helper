package io.sanchopansa.arkham;

import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.Ancient;
import io.sanchopansa.arkham.monsters.Gate;
import io.sanchopansa.arkham.monsters.Monster;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
import java.util.function.IntUnaryOperator;

/**
 * Этот класс описывает состояние партии
 *
 * @author SanchoPansa
 */

public class Game {
    private static final byte PLAYERS_LIMIT = 8;
    private static final byte SEALS_LIMIT = 6;
    private static final IntUnaryOperator GATE_LIMIT_FORMULA = (p) -> 9 - ((p + 1) / 2);
    private static final IntUnaryOperator MONSTER_LIMIT_FORMULA = (p) -> p + 3;
    private static final IntUnaryOperator OUTSKIRTS_LIMIT_FORMULA = (p) -> 8 - p;


    private final Deque<Investigator> players;
    private final Ancient ancient;
    private final Set<Gate> activeGates = new HashSet<>();
    private final Set<Monster> activeMonsters = new HashSet<>();
    private final Set<Monster> outskirtsMonsters = new HashSet<>();

    private final int gateLimit;
    private final int monstersLimit;
    private final int outskirtsLimit;


    private int terrorLevel = 0;
    private int doomLevel = 0;

    // Флажки состояний игры
    private boolean activeEnvironment = false;
    private boolean activeRumor = false;

    // Условия победы
    private int placedSeals = 0;

    public Game(Ancient ancient, Investigator... players) {
        this.ancient = ancient;
        int playersCount = players.length;
        if(players.length > PLAYERS_LIMIT) {
            throw new IllegalArgumentException("Number of players cannot exceed " + PLAYERS_LIMIT);
        }
        this.players = new ArrayDeque<>(Arrays.asList(players));
        this.gateLimit = GATE_LIMIT_FORMULA.applyAsInt(playersCount);
        this.monstersLimit = MONSTER_LIMIT_FORMULA.applyAsInt(playersCount);
        this.outskirtsLimit = OUTSKIRTS_LIMIT_FORMULA.applyAsInt(playersCount);
    }

    public Deque<Investigator> getPlayers() {
        return players;
    }

    public Ancient getAncient() {
        return ancient;
    }

    public int getGateLimit() {
        return gateLimit;
    }

    public int getMonsterLimit() {
        return this.monstersLimit;
    }

    public int getOutskirtsLimit() {
        return outskirtsLimit;
    }

    public Set<Gate> getActiveGates() {
        return activeGates;
    }

    public Set<Monster> getActiveMonsters() {
        return activeMonsters;
    }

    public Set<Monster> getOutskirtsMonsters() {
        return outskirtsMonsters;
    }

    public int getTotalGates() {
        return this.activeGates.size();
    }

    public int getTotalMonsters() {
        return this.activeMonsters.size();
    }

    public int getTotalOutskirtsMonsters() {
        return this.outskirtsMonsters.size();
    }

    public int getTerrorLevel() {
        return terrorLevel;
    }

    public void setTerrorLevel(int terrorLevel) {
        this.terrorLevel = terrorLevel;
    }

    public int getDoomLevel() {
        return doomLevel;
    }

    public void setDoomLevel(int doomLevel) {
        this.doomLevel = doomLevel;
    }

    public boolean hasActiveEnvironment() {
        return activeEnvironment;
    }

    public void setActiveEnvironment(boolean activeEnvironment) {
        this.activeEnvironment = activeEnvironment;
    }

    public boolean hasActiveRumor() {
        return activeRumor;
    }

    public void setActiveRumor(boolean activeRumor) {
        this.activeRumor = activeRumor;
    }

    public int getPlacedSeals() {
        return placedSeals;
    }

    public void setPlacedSeals(int placedSeals) {
        this.placedSeals = placedSeals;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("players", players)
                .append("ancient", ancient)
                .append("activeGates", activeGates)
                .append("activeMonsters", activeMonsters)
                .append("gateLimit", gateLimit)
                .append("monstersLimit", monstersLimit)
                .append("outskirtsLimit", outskirtsLimit)
                .append("outskirtsMonsters", outskirtsMonsters)
                .append("terrorLevel", terrorLevel)
                .append("doomLevel", doomLevel)
                .append("activeEnvironment", activeEnvironment)
                .append("activeRumor", activeRumor)
                .append("placedSeals", placedSeals)
                .toString();
    }
}
