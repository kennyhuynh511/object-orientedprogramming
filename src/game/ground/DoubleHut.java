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
public class DoubleHut extends Ground{
    /**
     * MonsterFactory class
     */

    private final MonsterFactory monsterFactory1;
    private final MonsterFactory monsterFactory2;
    private final MonsterFactory[] monsterFactoryArray;

    /**
     * Constructor.
     *
     */
    public DoubleHut(MonsterFactory monsterFactory1, MonsterFactory monsterFactory2) {
        super('h');
        this.monsterFactory1 = monsterFactory1;
        this.monsterFactory2 = monsterFactory2;
        monsterFactoryArray = null;
    }

    public DoubleHut(MonsterFactory[] monsterFactoryArray) {
        super('h');
        this.monsterFactoryArray = monsterFactoryArray;
        monsterFactory1 = null;
        monsterFactory2 = null;
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
        for (int i = 0; i < monsterFactoryArray.length; i++) {
            if (Math.random() <= this.monsterFactoryArray[i].getChance()) {
                Actor monster = this.monsterFactoryArray[i].createMonster();
                if (!gameMap.isAnActorAt(location)) {
                    gameMap.addActor(monster, location);
                } else {
                    for (Exit exit : location.getExits()) {
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
}
