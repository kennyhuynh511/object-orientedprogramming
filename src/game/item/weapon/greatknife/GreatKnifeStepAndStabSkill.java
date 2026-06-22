package game.item.weapon.greatknife;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.item.weapon.WeaponSkill;
/**
 * Class representing the GreatKnifeSkill
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */

public class GreatKnifeStepAndStabSkill implements WeaponSkill {
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
     *
     * Constructor of Skill
     */
    public GreatKnifeStepAndStabSkill(Actor actor, Location location){
        this.map = location.map();
        this.target = actor;
    }

    @Override
    public double activate(WeaponItem weaponItem, int staminaMaximum, int currentStamina) {
        double newStamina = currentStamina - (staminaMaximum * 0.25);
        if (newStamina >= 0) {
            if (target != null){
                Location location = map.locationOf(target);
                for (Exit exit : location.getExits()) {
                    Location destination = exit.getDestination();
                    Actor targetActor = destination.getActor();
                    if (targetActor != null){
                        if (targetActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                            int num = (int)(Math.random()*10);
                                if (num < 8){
                                    if (location.getExits().get((num)).getDestination().canActorEnter(targetActor)){
                                        map.moveActor(targetActor,location.getExits().get((num)).getDestination());
                                    }

                                }

                        }
                    }
                }
                this.target.hurt(weaponItem.damage());
                return newStamina;
            }
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
        return "Stab and step on " + target;
    }
    /**
     * The skill on the console menu
     * @return the activeState
     */
    @Override
    public boolean getActiveState() {
        return false;
    }
}
