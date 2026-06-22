package game.ground.burialgroundgate;

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
public class BurialGroundGate extends Ground {
    /**
     * The gate status set False as default
     */

    private boolean getStatus = false;
    /**
     * The method return the gameMap
     * @return gameMap class
     */
    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * The GameMap class variable
     */
    private final GameMap gameMap;

    /**
     * The Constructor
     * Assign the gameMap with the GameMap
     * @param gameMap The Gate need to know which type of gameMap its located
     */

    public BurialGroundGate(GameMap gameMap) {
        super('=');
        this.gameMap = gameMap;

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
                    actionList.add(new BurialGroundGateMoveAction(this));
                }
                else{
                actionList.add(new BurialGroundGateOpenAction(this));}
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
        return "Burial Ground gate";
    }

}


