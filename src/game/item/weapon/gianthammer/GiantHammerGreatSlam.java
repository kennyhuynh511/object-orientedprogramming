package game.item.weapon.gianthammer;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.item.weapon.WeaponSkill;
/**
 * Class representing the GiantHammerGreatSlam.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */
public class GiantHammerGreatSlam implements WeaponSkill {
    /**
     * Location
     */
    GameMap map;
    /**
     * Actor target
     */
    Actor target;
    /**
     * @param actor target
     * @param location location
     */
    public GiantHammerGreatSlam(Actor actor,Location location) {
        this.map = location.map();
        this.target = actor;
    }
    @Override
    public boolean getActiveState() {
        return false;
    }
    /**
     * Activate the skill
     * @param weaponItem active weapon
     * @param currentStamina: Current stamina
     * @param staminaMaximum : Maximum stamina
     * @return The stamina
     */
    @Override
    public double activate(WeaponItem weaponItem, int staminaMaximum, int currentStamina) {
        double newStamina = currentStamina - (staminaMaximum * 0.05);
        if (newStamina >= 0) {
            Location location = map.locationOf(target);
            for (Exit exit : location.getExits()) {
                Location destination = exit.getDestination();
                Actor targetActor = destination.getActor();
                if (targetActor != null) {
                    targetActor.hurt((int)(weaponItem.damage()*0.5));
                }
            }
            target.hurt(weaponItem.damage());
            return newStamina;
        }
        return currentStamina;

    }
    /**
     * Deactivate the skill
     * @param weaponItem active weapon
     */
    @Override
    public void deactivate(WeaponItem weaponItem) {}
    /**
     * Deactivate the skill
     * @param weaponItem active weapon
     */
    @Override
    public void tick(WeaponItem weaponItem) {}

    /**
     * The skill on the console menu
     * @return the name of the skills
     */
    @Override
    public String toString() {
        return "Great Slam skill on " + target;
    }
}
