package game.playerenemy;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.item.Flask;
import game.item.Heal;
import game.item.Runes;

public class EldentreeGuardian extends EnemyActor {
    /**
     * the possibility of the heal drop out
     */
    private final double HEAL_CHANCE = 0.25;
    /**
     * the possibility of the flask drop out
     */
    private final double FLASK_CHANCE = 0.15;
    /**
     * the value Runes of the HollowSoldier
     */
    private final int VALUE = 250;
    /**
     * Constructor
     *
     * @param name    The name of the Eldentree Guardian
     * @param displayChar The character of the Wandering Undead
     * @param hitPoints   The hit points of the Wandering Undead
     */

    public EldentreeGuardian(String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
        super(name,displayChar,hitPoints,hitRate,damage,verb);
        this.addCapability(Ability.GOD_ON_VOID); // let the actor can't be effect by pits
    }

    public EldentreeGuardian(Actor followActor, String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
        super(followActor, name,displayChar,hitPoints,hitRate,damage,verb);
        this.addCapability(Ability.GOD_ON_VOID); // let the actor can't be effect by pits
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
        if (Math.random() <= HEAL_CHANCE) {
            map.locationOf(this).addItem(new Heal());
        }
        if (Math.random()<= FLASK_CHANCE){
            map.locationOf(this).addItem(new Flask());
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
