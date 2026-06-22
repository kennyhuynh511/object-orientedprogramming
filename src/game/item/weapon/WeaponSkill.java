package game.item.weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
/**
 * Class representing the WeaponSkill interface.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public interface WeaponSkill {
    /**
     * Return the active or non-active status
     * @return the status true or false
     */
    boolean getActiveState();
    /**
     * Activate the skill
     * @param weaponItem active weapon
     * @param current: Current status
     * @param max : Maximum status
     */
    double activate(WeaponItem weaponItem, int max, int current);
    /**
     * Activate the skill
     * @param weaponItem non-active weapon
     */
    void deactivate(WeaponItem weaponItem);
    /**
     * count the turn
     * @param weaponItem non-active weapon
     */
    void tick(WeaponItem weaponItem);
    /**
     * Return the string on console
     * @return the string of the skills
     */
    String toString();
}
