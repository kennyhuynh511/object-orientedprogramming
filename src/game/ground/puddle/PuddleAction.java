package game.ground.puddle;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the Puddle Action.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public class PuddleAction extends Action {
    /**
     * Perform the action: Increase the stamina and heal the hit points
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(1);
        double increaseStamina = (actor.getAttributeMaximum(BaseActorAttributes.STAMINA)*0.01);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,(int)increaseStamina);
        return actor + " heal 1 points and 1% stamina now the heal is " + actor.getAttribute(BaseActorAttributes.HEALTH) +" and the stamina " + actor.getAttribute(BaseActorAttributes.STAMINA) ;
    }
    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drink the water in the puddle ";
    }
}
