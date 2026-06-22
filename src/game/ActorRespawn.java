package game;

import edu.monash.fit2099.engine.positions.GameMap;

public interface ActorRespawn{
    void resetMap(GameMap map);
    void respawn();
}
