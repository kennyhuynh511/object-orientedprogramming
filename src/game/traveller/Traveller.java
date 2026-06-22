package game.traveller;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.item.ItemStatus;
import game.item.weapon.broadsword.BroadSword;
import game.item.Flask;
import game.item.Heal;
import game.item.itempurchase.ItemBuyingPrice;
import game.monolouge.Conversation;
import game.monolouge.ConversationAction;

import java.util.ArrayList;


public class Traveller extends Actor implements Conversation {
    /**
     * The hashmap list
     */
    ArrayList<String> conversationList = new ArrayList<String>();
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
    public Traveller(GameMap map, Actor bossActor) { // ඞ Character
        super("Traveler",'ඞ',1);
        this.bossMap = map;
        this.bossActor = bossActor;
        this.addCapability(Status.CAN_NOT_RESET);
    }
    /**
     * The constructor of the Traveller class.
     */
    public Traveller() { // ඞ Character
        super("Traveler",'ඞ',1);
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
     * The Traveller can be selling and buying stuff
     * @param otherActor the Actor that might be performing the action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the actions of the Traveller
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        for (Item item : otherActor.getItemInventory()){
            if (item.hasCapability(ItemStatus.CAN_BE_SOLD)){
                actions.add(new BuyingAction((ItemBuyingPrice) item));
            }
        }
        actions.add(new SellingAction(new Heal()));
        actions.add(new SellingAction(new Flask()));
        actions.add(new SellingAction(new BroadSword()));
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
        this.conversationList = StringList(actor,map);
        int option = (int) (Math.random()*10);
        //int option = 7;
        if (option >= this.conversationList.size()){ // prevent errors
            return this + ": { " + this.conversationList.get(0) + " }";
        }
        return this + ": { " + this.conversationList.get(option) + " }";
    }
    /**
     * @param actor The player
     * @param map the map of the Forest Watcher
     * @return The list of monologue of the traveller
     */
    public ArrayList<String> StringList(Actor actor, GameMap map) {
        this.conversationList.add(0,"Of course, I will never give you up, valuable customer!");
        this.conversationList.add(1,"I promise I will never let you down with the quality of the items that I sell.");
        this.conversationList.add(2,"You can always find me here. I'm never gonna run around and desert you, dear customer!");
        this.conversationList.add(3,"I'm never gonna make you cry with unfair prices.");
        this.conversationList.add(4,"Trust is essential in this business. I promise I’m never gonna say goodbye to a valuable customer like you.");
        this.conversationList.add(5,"Don't worry, I’m never gonna tell a lie and hurt you.");
        if (map.contains(this.bossActor)){
            this.conversationList.add(6," You know the rules of this world, and so do I. Each area is ruled by a lord. Defeat the lord of this area, Abxervyer, and you may proceed to the next area.");
        }
        for(Item item : actor.getItemInventory()){
            if (item.hasCapability(ItemStatus.GIANT_HAMMER)){
                if(!map.contains(this.bossActor)){ // Defeat the forest Watcher
                    this.conversationList.add(6, "Congratulations on defeating the lord of this area. I noticed you still hold on to that hammer. Why don’t you sell it to me? We've known each other for so long. " +
                            "I can tell you probably don’t need that weapon any longer.");
                }
                this.conversationList.add(7, "Ooh, that’s a fascinating weapon you got there. I will pay a good price for it. You wouldn't get this price from any other guy.");
            }
        }
        return this.conversationList;
    }

}
