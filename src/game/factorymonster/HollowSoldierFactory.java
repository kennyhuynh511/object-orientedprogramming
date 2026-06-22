package game.factorymonster;

import game.playerenemy.EnemyActor;
import game.playerenemy.HollowSoldier;
/**
 * Class representing the HollowSoldierfactory
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */

public class HollowSoldierFactory implements MonsterFactory{

    /**
     * @return the monster - actor class
     */
    @Override
    public EnemyActor createMonster() {
        return new HollowSoldier("Hollow Soldier", '&',200,50,50,"slash");
    }
    /**
     * @return the chance / possibility in double
     */
    @Override
    public Double getChance() {
        return 0.1;
    }

}
