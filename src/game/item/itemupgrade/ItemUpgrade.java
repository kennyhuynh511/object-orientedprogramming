package game.item.itemupgrade;

import edu.monash.fit2099.engine.actors.Actor;
/**
 * Class representing the ItemUpgrade.
 * Created by:
 * @author Ngo Tran Ngoc Duy
 * @version 1.0
 */

public interface ItemUpgrade {
    /**
     * Calculate the buying price for an item based on certain conditions.
     *
     * @param actor  The actor buying the item.
     * @return The calculated upgrading price.
     */
    double returnUpgrading(Actor actor);
    /**
     * Calculate the buying price for an item based on certain conditions.
     * @return Original upgrading price.
     */
    double originalUpgrade();

}
