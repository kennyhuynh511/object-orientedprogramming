package game.factorymonster;

import game.WeatherEngine;
import game.playerenemy.EnemyActor;
import game.playerenemy.EldentreeGuardian;
import edu.monash.fit2099.engine.actors.Actor;
/**
 * Class representing the HollowSoldierfactory
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */

public class EldentreeGuardianFactory implements MonsterFactory{
    private Actor followActor;

    public EldentreeGuardianFactory() {
        this.followActor = null;
    }

    public EldentreeGuardianFactory(Actor actor) {
        this.followActor = actor;
    }

    /**
     * @return the monster - actor class
     */
    @Override
    public EnemyActor createMonster() {
        if (followActor != null) {
            return new EldentreeGuardian(followActor, "Eldentree Guardian", 'e', 250, 80, 50, "hits");
        } else {
            return new EldentreeGuardian("Eldentree Guardian", 'e', 250, 80, 50, "hits");
        }
    }
    /**
     * @return the chance / possibility in double
     */
    @Override
    public Double getChance() {
        return 0.2;
    }
}
