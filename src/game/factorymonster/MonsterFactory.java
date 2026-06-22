package game.factorymonster;

import game.playerenemy.EnemyActor;

/**
 * Class representing the MonsterFactory interface.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public interface MonsterFactory {
    /**
     * @return the monster - actor class
     */
    EnemyActor createMonster();
    /**
     * @return the chance / possibility in double
     */
    Double getChance();

}
