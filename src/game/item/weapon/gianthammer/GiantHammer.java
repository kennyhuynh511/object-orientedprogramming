package game.item.weapon.gianthammer;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.ActivateSkillAction;
import game.item.ItemStatus;
import game.item.itempurchase.ItemBuyingPrice;
/**
 * Class representing the GiantHammer.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */

public class GiantHammer extends WeaponItem implements ItemBuyingPrice {
    /**
     * Constructor of GiantHammer
     *
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "smashes",90);
        this.addCapability(ItemStatus.CAN_BE_SOLD);
        this.addCapability(ItemStatus.GIANT_HAMMER);
        this.addCapability(ItemStatus.CAN_NOT_UPGRADE);
    }

    /**
     * @return name of the BroadSword
     */
    public String toString(){
        return "Giant Hammer Room";
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
        actionList.add(new ActivateSkillAction(new GiantHammerGreatSlam(otherActor,location),this));
        return actionList;
    }
    /**
     * Handles the buying of the item without any option.
     *
     * @param option Determines if the item is being bought with a discount (true) or not (false).
     * @param actor  The actor buying the item.
     * @return The buying price of the item, and the item is removed from the actor's inventory.
     */

    @Override
    public double returnBuying(boolean option, Actor actor) {
        actor.removeItemFromInventory(this);
        return this.originalBuyingPrice();
    }
    /**
     * Determines the chance of a successful purchase of the item.
     *
     * @return false, indicating that the purchase is not subject to chance.
     */

    @Override
    public boolean originalBuyingChance() {
        return false;
    }
    /**
     * Retrieves the original buying price of the item.
     *
     * @return The original buying price of the item, a constant value.
     */

    @Override
    public double originalBuyingPrice() {
        return 250;
    }
}
