package game.ground.burialgroundgate;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
/**
 * Class representing the BurialGroundMoveAction.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */

public class BurialGroundGateMoveAction extends Action {
    /**
     * The Gate class
     */
    BurialGroundGate burialGroundGate;
    /**
     * The Constructor
     * Assign the gate class with the GameMap
     * @param burialGroundGate The gate class
     */
    public BurialGroundGateMoveAction(BurialGroundGate burialGroundGate) {
        this.burialGroundGate = burialGroundGate;
    }

    /**
     * Perform the action: Remove actor, get move the actor to the enxt map and locked the door
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        burialGroundGate.getGameMap().at(burialGroundGate.getGameMap().getXRange().min(),burialGroundGate.getGameMap().getYRange().min()).addActor(actor);
        burialGroundGate.setGetStatus(false);
        return actor + " set foot on the new map";
    }
    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " move to the new map";
    }
}
