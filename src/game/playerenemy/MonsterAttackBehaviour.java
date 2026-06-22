package game.playerenemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AttackAction;
import game.Status;
/**
 * Class representing the MonsterAttackBehaviour.
 * Modified by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */
public class MonsterAttackBehaviour implements Behaviour {
    /**
     * Aim to attack actor who has the Status.HOSTILE_TO_ENEMY
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            Actor targetActor = destination.getActor();
            if (targetActor != null) {
                if (targetActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(targetActor, exit.getName());
                }
            }
        }
        return null;

    }
}
