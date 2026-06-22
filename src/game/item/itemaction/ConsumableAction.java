package game.item.itemaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
/**
 * ConsumableAction class
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */

public class ConsumableAction extends Action {
    /**
     * Return the consumable class
     * @return the consumable class
     *
     */
    public Consumable getConsumable() {
        return consumable;
    }
    /**
     * The consumable class
     *
     */
    private Consumable consumable;
    /**
     * Constructors
     * @param consumable  the consumable class
     */
    public ConsumableAction(Consumable consumable) {
        this.consumable = consumable;
    }
    /**
     * Perform the action: Increase the actor status
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumable.consume(actor);
        return actor + " use the " + consumable;
    }
    /**
     * Actor consume the products
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " use " + getConsumable();
    }
}
