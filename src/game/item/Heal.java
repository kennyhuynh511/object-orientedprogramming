package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.item.itemaction.Consumable;
import game.item.itemaction.ConsumableAction;
import game.item.itempurchase.ItemBuyingPrice;
import game.item.itempurchase.ItemSellingPrice;
import game.item.itemupgrade.ItemUpgrade;

/**
 * A class that represents Heal class.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */

public class Heal extends Item implements Consumable, ItemBuyingPrice, ItemSellingPrice, ItemUpgrade {

    /***
     * @return the amount of percentage
     */
    public double getPercentage_change() {
        return percentage_change;
    }
    /***
     * Change the percentage.
     * @param percentage_change the amount of percentage
     */
    public void setPercentage_change(double percentage_change) {
        this.percentage_change = percentage_change;
    }
    /***
     * the percentage.
     *
     */
    private double percentage_change = 0.1;
    /***
     * Constructor.
     *
     */
    public Heal() {

        super("Healing Vial", 'a', true);
        this.addCapability(ItemStatus.HEAL);
        this.addCapability(ItemStatus.CAN_BE_SOLD);
    }
    /**
     * Print the name of the heal class
     */
    @Override
    public String toString(){
        return "Healing Vial";
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
        double increaseHitPoints = (actor.getAttributeMaximum(BaseActorAttributes.HEALTH)*getPercentage_change());
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,(int)increaseHitPoints);
        actor.removeItemFromInventory(this);
    }
    /**
     * Returns the selling price of the item and updates the actor's inventory.
     *
     * @param option Determines if the item is being sold with a discount (true) or not (false).
     * @param actor The actor selling the item.
     * @return The selling price of the item or 0.0 if the actor's balance is insufficient.
     */
    public double returnSelling(boolean option, Actor actor) {
        double price = this.originalSellingPrice();
        if (price > (double)actor.getBalance()) {
            return 0.0;
        } else if (option) {
            price *= 1.5;
            if (price <= (double)actor.getBalance()) {
                actor.addItemToInventory(this);
                return price;
            } else {
                return 0.0;
            }
        } else {
            actor.addItemToInventory(this);
            return price;
        }
    }
    /**
     * Returns the buying price of the item and removes it from the actor's inventory.
     *
     * @param option Determines if the item is being bought with a discount (true) or not (false).
     * @param actor The actor buying the item.
     * @return The buying price of the item.
     */

    public double returnBuying(boolean option, Actor actor) {
        double price = this.originalBuyingPrice();
        actor.removeItemFromInventory(this);
        return option ? price * 2.0 : price;
    }
    /**
     * Determines the chance of a successful sale of the item.
     *
     * @return true if the sale is successful, false otherwise.
     */

    public boolean originalSellingChance() {
        double chance = 0.25;
        return Math.random() <= chance;
    }
    /**
     * Determines the chance of a successful purchase of the item.
     *
     * @return true if the purchase is successful, false otherwise.
     */

    public boolean originalBuyingChance() {
        double chance = 0.1;
        return Math.random() <= chance;
    }
    /**
     * Retrieves the original buying price of the item.
     *
     * @return The original buying price of the item.
     */

    public double originalBuyingPrice() {
        return 35.0;
    }
    /**
     * Retrieves the original selling price of the item.
     *
     * @return The original selling price of the item.
     */

    public double originalSellingPrice() {
        return 100.0;
    }
    /**
     * Retrieves the upgrading price of the item.
     * @actor the customer
     * @return The upgrading price of the item.
     */
    @Override
    public double returnUpgrading(Actor actor) {
        if (actor.getBalance() - originalUpgrade()  < 0){
            return 0;
        }
        this.setPercentage_change(0.8);
        this.addCapability(ItemStatus.CAN_NOT_UPGRADE);
        return originalUpgrade();
    }
    /**
     * @return The original upgrading price of the item.
     */
    @Override
    public double originalUpgrade() {
        return 250;
    }
}
