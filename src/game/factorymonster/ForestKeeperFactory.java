package game.factorymonster;

import edu.monash.fit2099.engine.actors.Actor;
import game.Weather;
import game.WeatherEngine;
import game.playerenemy.EnemyActor;
import game.playerenemy.ForestKeeper;

/**
 * Class representing the HollowSoldierFactory
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 * Modified by:
 * @author Fabian WIse
 * @version 1.0
 */

public class ForestKeeperFactory implements MonsterFactory{
    /**
     * WeatherEngine class variable
     */
    WeatherEngine weatherEngine;
    /** Constructor
     * @param actor  the actor class input
     * @param weatherEngine the weatherEngine class
     */
    public ForestKeeperFactory(Actor actor, WeatherEngine weatherEngine) {
        this.followActor = actor;
        this.weatherEngine = weatherEngine;
    }
    /** Constructor
     * @param weatherEngine the weatherEngine class
     */
    public ForestKeeperFactory(WeatherEngine weatherEngine) {
        this.weatherEngine = weatherEngine;
    }

    /**
     * The followActor class
     */
    private Actor followActor;
    /** Constructor
     * @param actor  the actor class input
     */
    public ForestKeeperFactory(Actor actor) {
        this.followActor = actor;
        this.weatherEngine = new WeatherEngine();
    }
    /**
     * @return the monster - actor class
     */
    @Override
    public EnemyActor createMonster() {
        if (weatherEngine.hasWeather(Weather.SUNNY)) {
            // sunny spawn
            return new ForestKeeper(followActor, weatherEngine, "Forest Keeper", '8', 125, 75, 25, "slash");
        } else if (weatherEngine.hasWeather(Weather.RAINY)) {
            // rainy spawn
            // base code for rainy spawn
            return new ForestKeeper(followActor, weatherEngine, "Forest Keeper", '8', 125, 75, 25, "slash");
        } else {
            // default spawn
            return new ForestKeeper(followActor, weatherEngine, "Forest Keeper", '8', 125, 75, 25, "slash");
        }
    }

    /**
     * @return the chance / possibility in double
     */
    @Override
    public Double getChance() {
        double ORIGINAL_SPAWN_RATE = 0.15;
        if (weatherEngine != null) {
            if (weatherEngine.hasWeather(Weather.SUNNY)) {
                // sunny spawn
                return 2 * ORIGINAL_SPAWN_RATE;
            } else if (weatherEngine.hasWeather(Weather.RAINY)) {
                // rainy spawn
                // base code for rainy spawn
                return 0.15;
            } else {
                // default spawn
                return ORIGINAL_SPAWN_RATE;
            }
        }
        return ORIGINAL_SPAWN_RATE;
    }
}
