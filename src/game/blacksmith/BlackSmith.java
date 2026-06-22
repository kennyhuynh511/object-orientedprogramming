package game.blacksmith;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.item.ItemStatus;
import game.item.itemupgrade.ItemUpgrade;
import game.monolouge.Conversation;
import game.monolouge.ConversationAction;

import java.util.ArrayList;
/**
 * Class representing the BlackSmith.
 * Created by:
 * @author Ngo Tran Ngoc Duy
 * @version 1.0
 */

public class BlackSmith extends Actor implements Conversation {
    /**
     * The hashmap list
     */
    ArrayList<String> conversationArrayList = new ArrayList<String>();
    /**
     * The boss map
     */
    GameMap bossMap;
    /**
     * The boss actor
     */
    Actor bossActor;
    /**
     * @param map Map of the boss
     * The constructor of the Traveller class.
     */
    public BlackSmith(GameMap map, Actor bossActor) { // ඞ Character
        super("BlackSmith",'B',1);
        this.bossMap = map;
        this.bossActor = bossActor;
        this.addCapability(Status.CAN_NOT_RESET);
    }
    /**
     * The constructor of the Traveller class.
     */
    public BlackSmith() { // ඞ Character
        super("BlackSmith",'B',1);
    }
    /**
     * At each turn, doing nothing
     *
     * @param actions    collection of possible Actions for this Traveller
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the null
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
    /**
     * The BlackSmith take the action
     * @param otherActor the Actor that might be performing the action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the actions of the Traveller
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        for (Item item : otherActor.getItemInventory()){
            if (!item.hasCapability(ItemStatus.CAN_NOT_UPGRADE)){
                actions.add(new UpgradeAction((ItemUpgrade) item));
            }
        }
        actions.add(new ConversationAction(this,this.bossMap));
        return actions;
    }
    /**
     * @return The name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * @param actor The player
     * @param map the map of the Forest Watcher
     * @return The monologue
     */

    @Override
    public String MonolougeString(Actor actor,GameMap map) {
        this.conversationArrayList = StringList(actor,map);
        int option = (int) (Math.random()*10);
        //int option = 7;
        if (option >= this.conversationArrayList.size()){ // prevent errors
            return this + ": { " + this.conversationArrayList.get(0) + " }";
        }
        return this + ": { " + this.conversationArrayList.get(option) + " }";
    }
    /**
     * @param actor The player
     * @param map the map of the Forest Watcher
     * @return The list of monologue of the traveller
     */
    public ArrayList<String> StringList(Actor actor, GameMap map) {
        this.conversationArrayList.add(0,"I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.");
        this.conversationArrayList.add(1,"It’s dangerous to go alone. Take my creation with you on your adventure!");
        this.conversationArrayList.add(2,"Ah, it’s you. Let’s get back to make your weapons stronger.");
        if (map.contains(this.bossActor)){
            this.conversationArrayList.add(3,"Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!");
        }
        else{
            this.conversationArrayList.add(3, "Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.");
        }
        for(Item item : actor.getItemInventory()){
            if (item.hasCapability(ItemStatus.GREAT_KNIFE)){
                this.conversationArrayList.add(4, "Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.");
            }
        }
        return this.conversationArrayList;
    }
}
