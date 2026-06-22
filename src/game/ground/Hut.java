package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.*;
import game.factorymonster.MonsterFactory;

/**
 * Class representing the hut.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 * Modified by:
 * @author Fabian Wise
 * @version 1.0
 *
 */
public class Hut extends Ground{
    /**
     * MonsterFactory class
     */

    private final MonsterFactory monsterFactory;

    /**
     * Constructor.
     *
     */
    public Hut(MonsterFactory monsterFactory) {
        super('h');
        this.monsterFactory = monsterFactory;

    }
    /**
     * Returns true if an Actor can enter this location and false if can't
     *
     * @param actor the Actor who might be moving
     * @return true if the Actor can enter this location and false if the Actor don't have the ground Actor enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    /**
     * Create a monster by the chance
     * If there is an actor stand on the symbol, spawn elsewhere
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        GameMap gameMap = location.map();
        if (Math.random() <= this.monsterFactory.getChance()) {
            Actor monster = this.monsterFactory.createMonster();
            if (!gameMap.isAnActorAt(location)) {
                gameMap.addActor(monster, location);
            }
            else {
                for (Exit exit: location.getExits()) {
                    Location exitLocation = exit.getDestination();
                    if (!gameMap.isAnActorAt(exitLocation)) {
                        gameMap.addActor(monster, exitLocation);
                    }
                    break;
                }
            }
        }
    }


}
