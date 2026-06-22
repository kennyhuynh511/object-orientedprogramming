package game.item.weapon.broadsword;
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
 * Class representing the BroadSword.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */

public class BroadSword extends WeaponItem implements ItemSellingPrice, ItemBuyingPrice, ItemUpgrade {
    // 110 damage, 80% hit rate, char 1, skills: Focus: damage increase 10% for 5 turns, hit rates to 90%
    /**
     * BroadSwordFocusSkill class
     */
    private BroadSwordFocusSkill broadSwordFocusSkill;
    /**
     * @return the upgrade
     */
    public int getUpgrade() {
        return upgrade;
    }
    /**
     * @param upgrade change the upgrade
     */
    public void setUpgrade(int upgrade) {
        this.upgrade += upgrade;
    }

    /**
     * Amount of upgrade
     */
    private int upgrade = 0;
    /**
     * BroadSword constructor
     */
    public BroadSword(){
        super("BroadSword",'1',110,"hits",80);
        this.broadSwordFocusSkill =  new BroadSwordFocusSkill(80); // Get the hitRate as a parameter for the focus skills
        this.addCapability(ItemStatus.CAN_BE_SOLD);
    }
    /**
     * @return name of the BroadSword
     */
    public String toString(){
        return "BroadSword";
    }

    /**
     * Return options of broadSword skill when pick up the BroadSword
     * @param actor Actor who hold the sword
     * @return the action list
     */
    public ActionList allowableActions(Actor actor){ //No need to check stamina
        ActionList actionList = super.allowableActions(actor);
        if (!broadSwordFocusSkill.getActiveState()){ // Add the skill's option when the player active the skills
            actionList.add(new ActivateSkillAction(this.broadSwordFocusSkill,this));
        }
        return actionList;
    }

    /**
     * Called once per turn, so that the broadsword experience the turn moving
     * @param currentLocation Location of the broadSword
     * @param actor Actor who hold the sword
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // Create a method count the skills activate
        this.broadSwordFocusSkill.tick(this);
    }

    /**
     * Called once per turn, so that the broadsword experience the location
     * @param currentLocation Location of the broadSword
     */
    @Override
    public void tick(Location currentLocation) {
        if(this.broadSwordFocusSkill.getActiveState()){
            // check if the sword drop on the ground to deactivate
            this.broadSwordFocusSkill.deactivate(this);
        }
    }
    /** Return the amount of money
     * @param actor  the actor class input
     * @param option  The chance of taking special action
     */
    public double returnSelling(boolean option, Actor actor) {
        double price = this.originalSellingPrice();
        if (price > (double)actor.getBalance()) {
            return 0.0;
        } else if (!option) {
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
    /** Return the amount of money
     * @param actor  the actor class input
     * @param option  The chance of taking special action
     */
    public double returnBuying(boolean option, Actor actor) {
        actor.removeItemFromInventory(this);
        return this.originalBuyingPrice();
    }
    /** Return the chance of special action in selling
     */
    public boolean originalSellingChance() {
        double chance = 0.05;
        return Math.random() <= chance;
    }
    /** Return the original price
     */
    public double originalSellingPrice() {
        return 250.0;
    }

    /** Return the buying chance
     */
    public boolean originalBuyingChance() {
        return false;
    }
    /**
     * Return the amount of buying price
     */
    public double originalBuyingPrice() {
        return 100.0;
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
        this.setUpgrade(10);
        return originalUpgrade();
    }
    /**
     * @return The original price of the item.
     */
    @Override
    public double originalUpgrade() {
        return 1000;
    }
    /**
     * @return The damage
     */
    @Override
    public int damage() {
        return super.damage() + this.getUpgrade();
    }
}
