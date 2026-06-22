package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Ability;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Nguyen Khang Huynh\
 * @version 1.0
 *
 */
public class Floor extends Ground {
    /**
     * Constructor of floor
     */
    public Floor() {
        super('_');
    }
    /**
     * Returns true if an Actor can enter this location and false if can't
     *
     * @param actor the Actor who might be moving
     * @return true if the Actor can enter this location and false if the Actor don't have the ground Actor enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Ability.ALLOWABLE_TO_ENTER);
    }
}
