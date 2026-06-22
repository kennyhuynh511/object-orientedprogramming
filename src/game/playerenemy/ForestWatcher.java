package game.playerenemy;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.ground.DoubleGate.DoubleGate;
import game.item.Runes;

public class ForestWatcher extends EnemyActor {
    int weatherCounter = 0;
    GameMap gameMap1;
    GameMap gameMap2;
    /**
     * the value Runes of the ForestWatcher
     */
    private final int VALUE = 5000;
    /**
     * @return  gameMap the gameMap that need to be return
     */
    public GameMap getGameMap() {
        return gameMap1;
    }
    /**
     * @param gameMap the gameMap that modify
     */
    public void setGameMap(GameMap gameMap) {
        this.gameMap1 = gameMap;
    }

    private WeatherEngine weatherEngine;
    /**
     * Constructor
     *
     * @param gameMap1 the map that ForesterWatcher lead to
     */
    public ForestWatcher(Actor followActor,WeatherEngine weatherEngine, GameMap gameMap1, GameMap gameMap2) {
        super(followActor, "Forester Watcher",'Y',10,25,80,"punches");
        this.gameMap1 = gameMap1;
        this.gameMap2 = gameMap2;
        this.weatherEngine = weatherEngine;
        this.addCapability(Ability.GOD_ON_VOID); // let the actor can't be effect by pits
        this.addCapability(Status.BOSS);
        this.addCapability(Status.CAN_NOT_RESET);
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
        map.locationOf(this).addItem(new Runes(this.VALUE));
        map.locationOf(this).setGround(new DoubleGate(gameMap1, gameMap2, " Ancient Woods", " The Overgrown Sanctuary"));
        super.unconscious(actor,map);
        // PRINT THE MESSAGE
        for (String line : FancyMessage.BOSS_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return "";
    }

    /**
     * Method that can be executed when the Forester Watcher is unconscious due to natural causes or accident
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(GameMap map) {
        super.unconscious(map);
        return "Unlucky " + this + " see heaven";
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        playWeather();
        return super.playTurn(actions, lastAction, map, display);
    }
    /**
     * Method that take the action when the weather change
     */
    private void playWeather() {
        // count weather turn
        weatherCounter = (weatherCounter + 1) % 3;
        if (weatherCounter == 0) {
            // now alternate weather
            // alternate to rainy
            if (weatherEngine.hasWeather(Weather.SUNNY)) {
                weatherEngine.removeWeather(Weather.SUNNY);
                weatherEngine.addWeather(Weather.RAINY);
                System.out.println("rainy");
            } else { // alternate to sunny
                weatherEngine.removeWeather(Weather.RAINY);
                weatherEngine.addWeather(Weather.SUNNY);
                System.out.println("sunny");
            }
        }
    }
}
