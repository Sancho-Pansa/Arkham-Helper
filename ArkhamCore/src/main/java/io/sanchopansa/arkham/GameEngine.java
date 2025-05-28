package io.sanchopansa.arkham;

import io.sanchopansa.arkham.common.Phase;
import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.locations.Location;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GameEngine implements GameEngineInterface {
    private final Game gameInstance;
    private final GameVault vault;
    private final Graph<Location, DefaultEdge> map;

    public GameEngine(Game gameInstance, GameVault vault, Graph<Location, DefaultEdge> map) {
        this.gameInstance = gameInstance;
        this.vault = vault;
        this.map = map;
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
        PhaseIterator phaseIterator = new PhaseIterator();

        while(phaseIterator.hasNext() && gameInstance.getDoomLevel() < gameInstance.getAncient().getDoomTrack()) {
            Phase currentPhase = phaseIterator.next();
            switch(currentPhase) {
                case UPKEEP -> upkeep();
                case MOVEMENT -> movement();
                case ARKHAM_ENCOUNTER -> encounters();
                case OTHER_WORLD_ENCOUNTER -> otherWorld();
                case MYTHOS -> mythos();
            }
        }

    }

    @Override
    public void upkeep() {
        System.out.println("Upkeep");
        gameInstance.getPlayers().forEach(Investigator::restoreFocus);
    }

    @Override
    public void movement() {
        System.out.println("Movement");

    }

    @Override
    public void encounters() {
        System.out.println("Encounters");

    }

    @Override
    public void otherWorld() {
        System.out.println("Other World");

    }

    @Override
    public void mythos() {
        this.gameInstance.setDoomLevel(gameInstance.getDoomLevel() + 1);
        System.out.println("Mythos");

    }

    /**
     * Итератор обхода фаз.
     * По умолчанию является бесконечным и требует прерывания снаружи.
     */
    private static class PhaseIterator implements Iterator<Phase> {
        private final List<Phase> phases;
        private int index = -1;

        public PhaseIterator() {
            phases = Arrays.stream(Phase.values()).filter(phase -> !phase.equals(Phase.ANY)).toList();
        }

        @Override
        public boolean hasNext() {
            return index < Integer.MAX_VALUE;
        }

        /**
         * Начать с Мифа, затем идти по предзаданному порядку фаз
         * Если текущая фаза - Миф, то вернуться к Передышке.
         * @return Текущая фаза
         */
        @Override
        public Phase next() {
            Phase returnValue = Phase.MYTHOS;
            if(index != -1) {
                returnValue = phases.get(index % phases.size());
            }
            index++;
            return returnValue;
        }

        public int getIndex() {
            return index;
        }
    }
}
