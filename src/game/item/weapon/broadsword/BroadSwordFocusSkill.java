package game.item.weapon.broadsword;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.item.weapon.WeaponSkill;

/**
 * Class representing the BroadSword Focus skill.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */

public class BroadSwordFocusSkill implements WeaponSkill {
    /**
     * Count the turn
     */
    private int countTick = 0;
    /**
     * Original hit rate
     */
    private int orgHitRate;
    /**
     * Boolean active
     */
    private boolean isActive;

    /**
     * Constructor of the BroadSwordFocusSkill
     * Set the isActive to false as default
     * @param orgHitRate: Get the original hit rate from the broadSwordFocus
     */
    public BroadSwordFocusSkill(int orgHitRate){
        this.orgHitRate = orgHitRate;
        this.isActive = false; // Set the isActive to false as default
    }

    /**
     * Return the active status
     * @return the active status
     */
    @Override
    public boolean getActiveState() {
        return this.isActive;
    }

    /**
     * Activate the skills and increase the damage 10% every turn and set the hit rate to 90
     * Set the active status to True
     * @param weaponItem Input the weapon item
     * @param currentStamina: Current status
     * @param staminaMaximum : Maximum status
     */
    @Override
    public double activate(WeaponItem weaponItem, int staminaMaximum, int currentStamina) {
        double newStamina = currentStamina - (staminaMaximum * 0.2);
        if (newStamina < 0) {
            return currentStamina;
        }
        weaponItem.increaseDamageMultiplier(0.1F);
        weaponItem.updateHitRate(90);
        this.countTick = 0;
        this.isActive = true;
        return newStamina;
    }

    /**
     * DeActivate the skills and set hit rate and damage back to the default
     * Set the active status to False
     * Set the count tick back to 0
     * Print on the console to let the user know
     * @param weaponItem Input the weapon item
     */
    @Override
    public void deactivate(WeaponItem weaponItem) { // De-activate the skills
       weaponItem.updateDamageMultiplier(1.0f);
       weaponItem.updateHitRate(orgHitRate);
       this.countTick = 0;
       this.isActive = false;
       new Display().println("BroadSword focus skill deactivated");
    }
    /**
     * The skill on the console menu
     * @return the name of the skills
     */
    @Override
    public String toString() {
        return "BroadSword focus skill";
    }

    /**
     * Count the turn for the skill to 5
     * After 5 will run the method de-activate
     * @param weaponItem Input the weapon item
     */
    @Override
    public void tick(WeaponItem weaponItem){
        if (!this.isActive){ // Not active, break the function
            return;
        }
        if(this.countTick < 5){ // Count the tick
            this.countTick +=1;
            return;
        }
        deactivate(weaponItem); // If tick more than 5 run the deactivate function
    }
}
