package game.item.itemaction;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * the consumable interface
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public interface Consumable {
    /**
     * @param actor actor class
     *
     */
    void consume(Actor actor);

}
