package game.factorymonster;

import game.playerenemy.EnemyActor;
import game.playerenemy.LivingBranch;

/**
 * Class representing the HollowSoldierfactory
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */

public class LivingBranchFactory implements MonsterFactory{

    /**
     * @return the monster - actor class
     */
    @Override
    public EnemyActor createMonster() {
        return new LivingBranch("Living Branch", '?', 75, 90, 250, "hits");
    }
    /**
     * @return the chance / possibility in double
     */
    @Override
    public Double getChance() {
        return 0.9;
    }
}
