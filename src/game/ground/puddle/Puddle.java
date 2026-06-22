package game.ground.puddle;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing the Puddle
 * Modified by:
 * @author Nguyen Khang Huynh
 * @version 2.0
 *
 */
public class Puddle extends Ground {
    /**
     * Constructor
     *
     */
    public Puddle() {
        super('~');
    }
    /**
     * Returns true if an Actor can enter this location.
     * @param actor the Actor who might be moving
     * @return true if the Actor can enter this location
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
    /**
     * Returns an Action list of the Puddle
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();
        GameMap gameMap = location.map();
        if (actor != null){
            if (gameMap.locationOf(actor).y()== location.y() && gameMap.locationOf(actor).x()== location.x()){
                actionList.add(new PuddleAction());
            }
        }
        return actionList;

    }
    /**
     * Return the name of the puddle
     * @return the Puddle console name
     */

    @Override
    public String toString() {
        return "Puddle water";
    }

}
