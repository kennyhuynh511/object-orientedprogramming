package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Ability;

/**
 * Class representing the pits
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */

public class Pits extends Ground{
    /**
     * Constructor.
     *
     */
    public Pits() {
        super('+');
    }
    /**
     * Take the action to remove the actor when the actor steps on the pits
     * @param location get the location of the pits
     */
    @Override
    public void tick(Location location){
        GameMap gameMap = location.map();
        Actor actor = gameMap.getActorAt(location);
        if (actor != null && !actor.hasCapability(Ability.GOD_ON_VOID)){
                if (gameMap.locationOf(actor).y()== location.y() && gameMap.locationOf(actor).x()== location.x()){
                    actor.unconscious(gameMap);
                }
        }
    }
}

