package io.sanchopansa.arkham;

import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.locations.Location;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Deque;

public class GameEngine implements GameEngineInterface {
    private final Game gameInstance;
    private final GameVault vault;
    private final Graph<Location, DefaultEdge> map;
    private final GameListener gameListener;

    public GameEngine(Game gameInstance, GameVault vault, Graph<Location, DefaultEdge> map, GameListener gameListener) {
        this.gameInstance = gameInstance;
        this.vault = vault;
        this.map = map;
        this.gameListener = gameListener;
    }

    @Override
    public Game getGameInstance() {
        return gameInstance;
    }

    @Override
    public GameVault getVault() {
        return vault;
    }

    @Override
    public Graph<Location, DefaultEdge> getMap() {
        return map;
    }

    @Override
    public void launch() {
        proceedToNextPhase();
    }

    @Override
    public void proceedToNextPhase() {
        if(checkLossConditions()) {
            gameListener.onGameOver();
            return;
        }
        if(checkWinConditions()) {
            gameListener.onGameWin();
            return;
        }
        Phase p = this.gameInstance.getCurrentPhase();
        switch(p) {
            case UPKEEP -> upkeep();
            case MOVEMENT -> movement();
            case ARKHAM_ENCOUNTER -> encounters();
            case OTHER_WORLD_ENCOUNTER -> otherWorld();
            case MYTHOS -> mythos();
            default -> throw new IllegalStateException("Invalid Phase!" + p);
        }
    }

    @Override
    public void onPhaseComplete() {
        if(checkWinConditions()) {
            gameListener.onGameWin();
            return;
        }
        this.gameInstance.setCurrentPhase(nextPhase());
    }

    @Override
    public void upkeep() {
        System.out.println("Phase: Upkeep");
        gameListener.onPhaseStart(this.gameInstance, this.getVault());
        gameInstance.getPlayers().forEach(Investigator::restoreFocus);
    }

    @Override
    public void movement() {
        System.out.println("Phase: Movement");
    }

    @Override
    public void encounters() {
        System.out.println("Phase: Encounters");

    }

    @Override
    public void otherWorld() {
        System.out.println("Phase: Other World");

    }

    @Override
    public void mythos() {
        System.out.println("Phase: Mythos");
    }

    private boolean checkLossConditions() {
        Deque<Investigator> players = gameInstance.getPlayers();
        return players.stream().noneMatch(Investigator::isAlive);
    }

    private boolean checkWinConditions() {
        boolean areSealsPlaced = this.gameInstance.getPlacedSeals() >= Game.SEALS_LIMIT;
        boolean areGatesClosed = this.gameInstance.getActiveGates().isEmpty();
        int totalGateTrophies = this.gameInstance.getPlayers().stream()
                .map(Investigator::getGateTrophies)
                .reduce(0,
                        (sum, gates) -> sum + gates.size(),
                        Integer::sum
                );
        return areSealsPlaced || areGatesClosed && totalGateTrophies >= this.gameInstance.getPlayers().size();
    }

    private Phase nextPhase() {
        Phase phase = this.gameInstance.getCurrentPhase();
        return switch(phase) {
            case UPKEEP -> Phase.MOVEMENT;
            case MOVEMENT -> Phase.ARKHAM_ENCOUNTER;
            case ARKHAM_ENCOUNTER -> Phase.OTHER_WORLD_ENCOUNTER;
            case OTHER_WORLD_ENCOUNTER -> Phase.MYTHOS;
            case MYTHOS -> Phase.UPKEEP;
            default -> throw new IllegalStateException("Wrong Phase detected: " + phase);
        };
    }
}
