package game.playerenemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
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

public class ForestKeeper extends EnemyActor {
    WeatherEngine weatherEngine;
    /**
     * the possibility of the heal drop out
     */
    private final double HEAL_CHANCE = 0.2; // 0.2
    /**
     * the value Runes of the ForestKeeper
     */
    private final int VALUE = 50;

    /**
     * Constructor
     *
     * @param name    The name of the Wandering Undead
     * @param displayChar The character of the Wandering Undead
     * @param hitPoints   The hit points of the Wandering Undead
     * @param verb The action
     * @param damage The amount of damage
     * @param hitRate the amount of hit rate
     */

    public ForestKeeper(String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
        super(name,displayChar,hitPoints,hitRate,damage,verb);
        this.weatherEngine = new WeatherEngine();
    }
    /**
     * Constructor
     *
     * @param name    The name of the
     * @param displayChar The character
     * @param hitPoints   The hit points
     * @param verb The action
     * @param damage The amount of damage
     * @param hitRate the amount of hit rate
     * @param weatherEngine The weatherEngine class
     *
     */
    public ForestKeeper(WeatherEngine weatherEngine, String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
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
     * @param weatherEngine The WeatherEngine class
     */
    public ForestKeeper(Actor followActor, WeatherEngine weatherEngine, String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
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
     * Return the action in each turn
     * @param map Map class
     * @param display Display class
     * @param actions The actions that need to do
     * @param lastAction The previous action
     * @return the action that needed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (weatherEngine.hasWeather(Weather.RAINY)) {
            this.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 10);
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}