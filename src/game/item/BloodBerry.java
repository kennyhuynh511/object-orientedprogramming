package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.item.itemaction.Consumable;
import game.item.itemaction.ConsumableAction;
import game.item.itempurchase.ItemBuyingPrice;

/**
 * Class representing the BloodBerry
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */

public class BloodBerry extends Item implements Consumable, ItemBuyingPrice {

    /**
     * Constructor.
     */
    public BloodBerry() {
        super("BloodBerry", '*',true);
        this.addCapability(ItemStatus.INCREASE_MAX_HITPOINTS);
        this.addCapability(ItemStatus.CAN_BE_SOLD);
        this.addCapability(ItemStatus.CAN_NOT_UPGRADE);
    }
    /**
     * Print the name of the BloodBerry class
     */
    @Override
    public String toString(){
        return "BloodBerry";
    }


    /**
     * List of allowable actions that the item can perform to the current actor
     * @param owner the actor that owns the item
     * @return a list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionlist = new ActionList();
        actionlist.add(new ConsumableAction(this));
        return actionlist;
    }
    /**
     * @param actor actor class
     * Increase the hit points
     */
    @Override
    public void consume(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,5);
        actor.removeItemFromInventory(this);
    }
    /** Return the amount of money
     * @param actor  the actor class input
     * @param option  The chance of taking special action
     */
    public double returnBuying(boolean option, Actor actor) {
        actor.removeItemFromInventory(this);
        return this.originalBuyingPrice();
    }
    /** Return the chance of special action in buying
     */
    public boolean originalBuyingChance() {
        return false;
    }
    /** Return the original price
     */
    public double originalBuyingPrice() {
        return 10.0;
    }
}
