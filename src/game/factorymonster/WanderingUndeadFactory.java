package game.factorymonster;

import game.playerenemy.EnemyActor;
import game.playerenemy.WanderingUndead;

/**
 * Class representing the Wandering Undead factory
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public class WanderingUndeadFactory implements MonsterFactory {

    /**
     * return the chance / possibility in double
     */
    @Override
    public Double getChance() {
        return 0.25;
    }

    /**
     * return the monster - actor class
     */
    @Override
    public EnemyActor createMonster() {
        return new WanderingUndead("Wandering Undead", 't', 100,50,30,"bites");
    }
}
