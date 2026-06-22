package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Nguyen Khang Huynh
 *
 */
public class Wall extends Ground {
    /**
     * Constructor
     */
    public Wall() {
        super('#');
    }
    /**
     * Returns true if an Actor can enter this location.
     * @param actor the Actor who might be moving
     * @return true if the Actor can enter this location and fasle if it can't
     */

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
