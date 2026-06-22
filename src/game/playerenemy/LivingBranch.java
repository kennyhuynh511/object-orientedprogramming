package game.playerenemy;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.item.BloodBerry;
import game.item.Flask;
import game.item.Heal;
import game.item.Runes;

public class LivingBranch extends EnemyActor {
    /**
     * the possibility of the heal drop out
     */
    private final double BLOODBERRY_CHANCE = 0.5;

    /**
     * the value Runes of the HollowSoldier
     */
    private final int VALUE = 500;
    /**
     * Constructor
     *
     * @param name    The name of the Eldentree Guardian
     * @param displayChar The character of the Wandering Undead
     * @param hitPoints   The hit points of the Wandering Undead
     */

    public LivingBranch(String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
        super(name,displayChar,hitPoints,hitRate,damage,verb);
        this.addCapability(Ability.GOD_ON_VOID); // let the actor can't be effect by pits
        this.behaviours.remove(this.behaviours.size()-1); // Living Branch can't wander.
    }

    /**
     * Method that can be executed when the Hollow Soldier is unconscious due to the action of another actor
     * It will drop the keys once the
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        if (Math.random() <= BLOODBERRY_CHANCE) {
            map.locationOf(this).addItem(new BloodBerry());
        }
        map.locationOf(this).addItem(new Runes(this.VALUE));
        super.unconscious(actor,map);
        return this + " opens eyes and see heaven";

    }

    /**
     * Method that can be executed when the Hollow Soldier is unconscious due to natural causes or accident
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(GameMap map) {
        super.unconscious(map);
        return "Unlucky " + this + " see heaven";
    }
}
