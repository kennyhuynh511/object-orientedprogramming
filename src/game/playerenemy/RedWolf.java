package game.playerenemy;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Weather;
import game.WeatherEngine;
import game.item.Heal;
import game.item.Runes;

/**
 * Class representing the Wandering Undead.
 * Modified by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 * Modified by:
 * @author Fabian Wise
 * @version 1.0
 */

public class RedWolf extends EnemyActor {
    /**
     * the possibility of the heal drop out
     */
    private final double HEAL_CHANCE = 0.1; // 0.1
    /**
     * the value Runes of the RedWolf
     */
    private final int VALUE = 25;

    private WeatherEngine weatherEngine;
    /**
     * Constructor
     *
     * @param name    The name
     * @param displayChar The character
     * @param hitPoints   The hit points
     * @param verb The action
     * @param damage The amount of damage
     * @param hitRate the amount of hit rate
     * @param weatherEngine The weatherEngine class
     *
     */
    public RedWolf(WeatherEngine weatherEngine, String name, char displayChar, int hitPoints, int hitRate, int damage, String verb) {
        super(name,displayChar,hitPoints,hitRate,damage,verb);
        this.weatherEngine = weatherEngine;
    }
    /**
     * Constructor
     *
     * @param name    The name
     * @param displayChar The character
     * @param hitPoints   The hit points
     * @param verb The action
     * @param damage The amount of damage
     * @param hitRate the amount of hit rate
     * @param followActor The Actor class
     *
     */
    public RedWolf(Actor followActor, String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
        super(followActor, name,displayChar,hitPoints,hitRate,damage,verb);
    }
    /**
     * Constructor
     *
     * @param name    The name
     * @param displayChar The character
     * @param hitPoints   The hit points
     * @param verb The action
     * @param damage The amount of damage
     * @param hitRate the amount of hit rate
     * @param followActor The Actor class
     * @param weatherEngine The weatherEngine class
     *
     */
    public RedWolf(Actor followActor, WeatherEngine weatherEngine, String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
        super(followActor, name,displayChar,hitPoints,hitRate,damage,verb);
        this.weatherEngine = weatherEngine;
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
    /**
     * Return IntrinsicWeapon
     * @return the IntrinsicWeapon class
     *
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        if (weatherEngine.hasWeather(Weather.SUNNY)) {
            // weapon when sunny
            return new IntrinsicWeapon(3 * this.damage,this.verb, this.hitRate);
        } else if (weatherEngine.hasWeather(Weather.RAINY)) {
            // base code for rainy
            return new IntrinsicWeapon(this.damage,this.verb, this.hitRate);
        } else {
            // default
            return new IntrinsicWeapon(this.damage,this.verb, this.hitRate);
        }
    }
}