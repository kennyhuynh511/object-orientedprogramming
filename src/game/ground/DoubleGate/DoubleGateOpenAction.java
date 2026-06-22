package game.ground.DoubleGate;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the BurialGroundOpenAction.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public class DoubleGateOpenAction extends Action {
    /**
     * The Gate class
     */
    DoubleGate doubleGate;

    /**
     * The Constructor
     * Assign the gate class with the GameMap
     * @param doubleGate The gate class
     */
    public DoubleGateOpenAction(DoubleGate doubleGate) {
        this.doubleGate = doubleGate;
    }
    /**
     * Perform the action: Change the gate status
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        doubleGate.setGetStatus(true);
        return this.doubleGate.toString() + " are unlocked ";
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlock the " + this.doubleGate.toString();
    }
}
