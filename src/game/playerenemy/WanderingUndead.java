package game.playerenemy;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.BurialGroundKey;
import game.item.Heal;
import game.item.Runes;

/**
 * Class representing the Wandering Undead.
 * Modified by:
 * @author Nguyen Khang Huynh
 * @version 2.0
 */

public class WanderingUndead extends EnemyActor {
    /**
     * the possibility of the old key drop out
     */
    private final double OLD_KEY_CHANCE = 0.25; //0.25
    /**
     * the possibility of the old key drop out
     */
    private final double HEAL_CHANCE = 0.2; //0.2
    /**
     * the value Runes of the WanderingUndead
     */
    private final int VALUE = 50;

    /**
     * Constructor
     *
     * @param name    The name of the Wandering Undead
     * @param displayChar The character of the Wandering Undead
     * @param hitPoints   The hit points of the Wandering Undead
     */

    public WanderingUndead(String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
        super(name,displayChar,hitPoints,hitRate,damage,verb);
    }

    /**
     * Method that can be executed when the Wandering Undead is unconscious due to the action of another actor
     * It will drop the keys once the
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        if (Math.random() <= OLD_KEY_CHANCE) {
            map.locationOf(this).addItem(new BurialGroundKey());
        }
        if (Math.random() <= HEAL_CHANCE){
            map.locationOf(this).addItem(new Heal());
        }
        map.locationOf(this).addItem(new Runes(this.VALUE));
        super.unconscious(actor,map);
        return this + " opens eyes and see heaven";

    }

    /**
     * Method that can be executed when the Wandering Undead is unconscious due to natural causes or accident
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(GameMap map) {
        super.unconscious(map);
        return "Unlucky " + this + " see heaven";
    }
}
