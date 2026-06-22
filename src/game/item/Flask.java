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
 * A class that represents Flask.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */

public class Flask extends Item implements Consumable, ItemBuyingPrice, ItemSellingPrice, ItemUpgrade {
    /***
     * @return the amount of percentage
     */
    public double getPercentage() {
        return percentage;
    }
    /***
     * Change the percentage.
     * @param percentage_change the amount of percentage
     */
    public void setPercentage(double percentage_change) {
        this.percentage = percentage_change;
    }
    /**
     * the percentage.
     */
    private double percentage = 0.2;
    /**
     * Constructor.
     *
     */
    public Flask() {
        super("Refreshing Flask", 'u', true);
        this.addCapability(ItemStatus.STAMINA_RECOVER);
        this.addCapability(ItemStatus.CAN_BE_SOLD);
    }
    /**
     * Print the name of the heal class
     */
    @Override
    public String toString(){
        return "Refreshing Flask";
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
     * Increase the stamina
     */
    @Override
    public void consume(Actor actor) {
        double increaseStamina = (actor.getAttributeMaximum(BaseActorAttributes.STAMINA)*this.getPercentage());
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,(int)increaseStamina);
        actor.removeItemFromInventory(this);
    }
    /**
     * Calculates the selling price of the item based on certain conditions.
     *
     * @param option Determines if the item is being sold with a discount (true) or not (false).
     * @param actor  The actor selling the item.
     * @return The selling price of the item or 0.0 if the actor's balance is insufficient.
     */
    public double returnSelling(boolean option, Actor actor) {
        double price = this.originalSellingPrice();
        if (price > (double)actor.getBalance()) {
            return 0.0;
        } else if (option) {
            price *= 0.8;
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
     * Handles the buying of the item based on certain conditions.
     *
     * @param option Determines if the item is being bought with a discount (true) or not (false).
     * @param actor  The actor buying the item.
     * @return The buying price of the item or 0.0 if the item cannot be purchased with a discount.
     */

    public double returnBuying(boolean option, Actor actor) {
        actor.removeItemFromInventory(this);
        return option ? 0.0 : this.originalBuyingPrice();
    }
    /**
     * Determines the chance of a successful sale of the item.
     *
     * @return true if the sale is successful, based on a predefined chance.
     */

    public boolean originalSellingChance() {
        double chance = 0.1;
        return Math.random() <= chance;
    }
    /**
     * Determines the chance of a successful purchase of the item.
     *
     * @return true if the purchase is successful, based on a predefined chance.
     */

    public boolean originalBuyingChance() {
        double chance = 0.5;
        return Math.random() <= chance;
    }
    /**
     * Retrieves the original buying price of the item.
     *
     * @return The original buying price of the item, a constant value.
     */

    public double originalBuyingPrice() {
        return 25.0;
    }
    /**
     * Retrieves the original selling price of the item.
     *
     * @return The original selling price of the item, a constant value.
     */

    public double originalSellingPrice() {
        return 75.0;
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
        this.setPercentage(1.0);
        this.addCapability(ItemStatus.CAN_NOT_UPGRADE);
        return originalUpgrade() ;
    }
    /**
     * @return The original price of the item.
     */
    @Override
    public double originalUpgrade() {
        return 175;
    }
}
