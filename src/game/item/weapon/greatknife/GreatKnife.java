package game.item.weapon.greatknife;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.ActivateSkillAction;
import game.item.ItemStatus;
import game.item.itempurchase.ItemBuyingPrice;
import game.item.itempurchase.ItemSellingPrice;
import game.item.itemupgrade.ItemUpgrade;

/**
 * Class representing the GreatKnife.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */

public class GreatKnife extends WeaponItem implements ItemBuyingPrice, ItemSellingPrice, ItemUpgrade {
    /**
     * GreatKnife constructor
     */
    public GreatKnife(){
        super("Great Knife",'>',75,"stabs",70);
        this.addCapability(ItemStatus.CAN_BE_SOLD);
        this.addCapability(ItemStatus.GREAT_KNIFE);
    }
    /**
     * @return name of the GreatKnife
     */
    public String toString(){
        return "GreatKnife";
    }

    /**
     * Active the skill on other actor
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actionList = super.allowableActions(otherActor,location);
        actionList.add(new ActivateSkillAction(new GreatKnifeStepAndStabSkill(otherActor,location),this));
        return actionList;
    }
    /**
     * Calculates the buying price of the item based on certain conditions.
     *
     * @param option Determines if the item is being bought with a discount (true) or not (false).
     * @param actor  The actor buying the item.
     * @return The buying price of the item or a negative value if the item cannot be purchased due to an insufficient balance.
     */
    @Override
    public double returnBuying(boolean option, Actor actor) {
        if (!option){
            if (actor.getBalance() < this.originalBuyingPrice()){
                return (-1)*(actor.getBalance());
            }
            return (-1)*this.originalBuyingPrice();
        }

        actor.removeItemFromInventory(this);
        return this.originalBuyingPrice();


    }
    /**
     * Determines the chance of a successful purchase of the item.
     *
     * @return true if the purchase is successful, based on a predefined chance.
     */
    @Override
    public boolean originalBuyingChance() {
        double chance = 0.1;
        return Math.random() <= chance;
    }
    /**
     * Retrieves the original buying price of the item.
     *
     * @return The original buying price of the item, a constant value.
     */

    @Override
    public double originalBuyingPrice() {
        return 175;
    }
    /**
     * Calculates the selling price of the item based on certain conditions.
     *
     * @param option Determines if the item is being sold with a discount (true) or not (false).
     * @param actor  The actor selling the item.
     * @return The selling price of the item or 0.0 if the actor's balance is insufficient.
     */

    @Override
    public double returnSelling(boolean option, Actor actor) {
        double price = this.originalSellingPrice();
        if (price > (double)actor.getBalance()) {
            return 0.0;
        }
        else if (option) {
            price *= 3;
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
     * Determines the chance of a successful sale of the item.
     *
     * @return true if the sale is successful, based on a predefined chance.
     */

    @Override
    public boolean originalSellingChance() {
        double chance = 0.05;
        return Math.random() <= chance;
    }
    /**
     * Retrieves the original selling price of the item.
     *
     * @return The original selling price of the item, a constant value.
     */

    @Override
    public double originalSellingPrice() {
        return 300;
    }
    /**
     * Retrieves the upgrading price of the item.
     * @actor the customer
     * @return The upgrading price of the item.
     */
    @Override
    public double returnUpgrading(Actor actor) {
        if (actor.getBalance() - originalUpgrade() < 0){
            return 0;
        }
        this.increaseHitRate(1);
        return originalUpgrade();
    }
    /**
     * @return The original price of the item.
     */
    @Override
    public double originalUpgrade() {
        return 2000;
    }
}
