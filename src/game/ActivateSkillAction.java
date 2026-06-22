package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.item.weapon.WeaponSkill;

/**
 * Class representing the ActivateSkillAction.
 * Modified by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */
public class ActivateSkillAction extends Action {
    /**
     * Weapon skill class
     */
    WeaponSkill weaponSkill;
    /**
     * Weapon Item class
     */
    WeaponItem weaponItem;

    /**
     * Constructor and gained the input outside the class
     * @param weaponItem weaponItem class input
     * @param weaponSkill WeaponSkill class input
     */
    public ActivateSkillAction(WeaponSkill weaponSkill, WeaponItem weaponItem) {
        this.weaponSkill = weaponSkill;
        this.weaponItem = weaponItem;
    }

    /**
     * The execute override
     * @param actor actor who execute the code
     * @param map the map of the current location
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        int staminaMaximum = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int currentStamina = actor.getAttribute(BaseActorAttributes.STAMINA);
        double newStamina = weaponSkill.activate(this.weaponItem,staminaMaximum,currentStamina);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE, (int) newStamina);
        return actor + " has activated the skill: " + this.weaponSkill.toString();

    }
    /**
     * The menu description override
     * @param actor actor who execute the code
     * @return the message on the console menu
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " will activate the skill: " + this.weaponSkill.toString();
    }
}
