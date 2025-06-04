package io.sanchopansa.arkham;

import io.sanchopansa.arkham.locations.Location;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public interface GameEngineInterface {
    Game getGameInstance();
    GameVault getVault();
    Graph<Location, DefaultEdge> getMap();
    void launch();

    void proceedToNextPhase();
    void onPhaseComplete();

    void upkeep();
    void movement();
    void encounters();
    void otherWorld();
    void mythos();
}
