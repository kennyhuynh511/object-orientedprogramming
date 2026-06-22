package game.ground.DoubleGate;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.item.ItemStatus;

/**
 * Class representing the BurialGround.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public class DoubleGate extends Ground {
    /**
     * The method return the gameMap
     * @return gameMap class
     */
    public GameMap getGameMap() {
        return gameMap1;
    }

    /**
     * The GameMap class variable
     */
    private final GameMap gameMap1;
    private final GameMap gameMap2;
    private final String mapString1;
    private final String mapString2;
    private final GameMap[] gameMapArray;
    private final String[] mapStringArray;


    /**
     * The Constructor
     * Assign the gameMap with the GameMap
     * @param gameMap1 The Gate need to know which type of gameMap its located
     */

    public DoubleGate(GameMap gameMap1, GameMap gameMap2, String mapString1, String mapString2) {
        super('=');
        this.gameMap1 = gameMap1;
        this.gameMap2 = gameMap2;
        this.mapString1 = mapString1;
        this.mapString2 = mapString2;
        this.gameMapArray = null;
        this.mapStringArray = null;
    }

    public DoubleGate(GameMap[] gameMapArray, String[] mapStringArray) {
        super('=');
        this.gameMap1 = null;
        this.gameMap2 = null;
        this.mapString1 = null;
        this.mapString2 = null;
        this.gameMapArray = gameMapArray;
        this.mapStringArray = mapStringArray;
    }
    /**
     *Get the gate status
     * @return the boolean status of the gate
     */

    public boolean isGetStatus() {
        return getStatus;
    }
    /**
     *Chane the gate status
     * @param getStatus Change the gate status
     */
    public void setGetStatus(boolean getStatus) {
        this.getStatus = getStatus;
    }
    /**
     * The gate status set False as default
     */
    private boolean getStatus = false;

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();
        for (Item item: actor.getItemInventory()){
            if (item.hasCapability(ItemStatus.UNLOCK_GATE)){
                if (this.isGetStatus()){
                    // add two actions for the two maps.
                    actionList.add(new DoubleGateMoveAction(this, gameMap1, mapString1));
                    actionList.add(new DoubleGateMoveAction(this, gameMap2, mapString2));
                }
                else{
                    actionList.add(new DoubleGateOpenAction(this));}
            }
        }
        return actionList;

    }
    /**
     * Return the name of the burial ground gate
     * @return the burial Ground gate console name
     */

    @Override
    public String toString() {
        return "Double Gate";
    }

}


