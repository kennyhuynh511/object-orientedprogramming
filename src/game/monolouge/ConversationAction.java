package game.monolouge;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;


/**
 * Class Action that help player can talk to the actor
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public class ConversationAction extends Action {
    /**
     * Conversation class
     */
    Conversation conversation;
    /**
     * Map class
     */
    GameMap map;
    /**
     * Constructor
     * @param conversation Conversation class input
     * @param map Map of the boss
     */
    public ConversationAction(Conversation conversation,GameMap map) {
        this.conversation = conversation;
        this.map = map;
    }
    /**
     * Perform the Action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
       return this.conversation.MonolougeString(actor,this.map);
    }
    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to the " + this.conversation;
    }
}
