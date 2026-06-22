package game.factorymonster;

import edu.monash.fit2099.engine.actors.Actor;
import game.Weather;
import game.WeatherEngine;
import game.playerenemy.EnemyActor;
import game.playerenemy.RedWolf;

/**
 * Class representing the HollowSoldierfactory
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 * Modified by:
 * @author Fabian WIse
 * @version 1.0
 */

public class RedWolfFactory implements MonsterFactory{
    /**
     * The actor class
     */
    private Actor followActor;

    /**
     * WeatherEngine classs
     */
    WeatherEngine weatherEngine;
    /** Constructor of RedWolfFactory
     */
    public RedWolfFactory() {
        this.weatherEngine = new WeatherEngine();
    }

    /** Constructor
     * @param weatherEngine the weatherEngine class
     */
    public RedWolfFactory(WeatherEngine weatherEngine) {
        this.weatherEngine = weatherEngine;
    }
    /** Constructor
     * @param actor  the actor class input
     */
    public RedWolfFactory(Actor actor) {
        this.followActor = actor;
        this.weatherEngine = new WeatherEngine();
    }
    /** Constructor
     * @param actor  the actor class input
     * @param weatherEngine the weatherEngine class
     */
    public RedWolfFactory(Actor actor, WeatherEngine weatherEngine) {
        this.weatherEngine = weatherEngine;
        this.followActor = actor;
    }


    /** Class that return the RedWolfFactory
     * @return the monster - actor class
     */
    @Override
    public EnemyActor createMonster() {
        if (this.followActor != null) {
            if (weatherEngine.hasWeather(Weather.SUNNY)) {
                // sunny spawn
                return new RedWolf(this.followActor, weatherEngine, "Red Wolf", 'r', 25, 80, 15, "bites");
            } else if (weatherEngine.hasWeather(Weather.RAINY)) {
                // rainy spawn
                // base code for rainy spawn
                return new RedWolf(this.followActor, weatherEngine, "Red Wolf", 'r', 25, 80, 15, "bites");
            } else {
                // default spawn
                return new RedWolf(this.followActor, weatherEngine, "Red Wolf", 'r', 25, 80, 15, "bites");
            }
        } else {
            if (weatherEngine.hasWeather(Weather.SUNNY)) {
                // sunny spawn
                return new RedWolf(weatherEngine, "Red Wolf", 'r', 25, 80, 15, "bites");
            } else if (weatherEngine.hasWeather(Weather.RAINY)) {
                // rainy spawn
                // base code for rainy spawn
                return new RedWolf(weatherEngine, "Red Wolf", 'r', 25, 80, 15, "bites");
            } else {
                // default spawn
                return new RedWolf(weatherEngine, "Red Wolf", 'r', 25, 80, 15, "bites");
            }
        }
    }
    /**
     * @return the chance / possibility in double
     */
    @Override
    public Double getChance() {
        double ORIGINAL_SPAWN_RATE = 0.3;
        if (weatherEngine != null) {
            if (weatherEngine.hasWeather(Weather.RAINY)) {
                // rainy spawn
                return 0.45;
            }
        }
        return ORIGINAL_SPAWN_RATE;
    }
}