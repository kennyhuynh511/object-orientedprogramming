package game.monolouge;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;

/**
 * Interface of conversation
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */

public interface Conversation {
    /**
     * @param actor The player
     * @param map the map of the Forest Watcher
     * @return The monologue
     */
    String MonolougeString(Actor actor,GameMap map);
    /**
     * @param actor The player
     * @param map the map of the Forest Watcher
     * @return The list of monologues
     */
    ArrayList<String> StringList(Actor actor, GameMap map);

}
