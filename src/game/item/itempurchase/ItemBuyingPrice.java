package game.item.itempurchase;

import edu.monash.fit2099.engine.actors.Actor;
/**
 * Class representing the ItemBuyingPrice.
 * Created by:
 * @author Ngo Tran Ngoc Duy
 * @version 1.0
 */

public interface ItemBuyingPrice {
    /**
     * Calculate the buying price for an item based on certain conditions.
     *
     * @param option The buying option.
     * @param actor  The actor buying the item.
     * @return The calculated buying price.
     */
    double returnBuying(boolean option, Actor actor);

    /**
     * Check if the original buying chance is valid.
     *
     * @return true if the original buying chance is valid, false otherwise.
     */
    boolean originalBuyingChance();

    /**
     * Get the original buying price of an item.
     *
     * @return The original buying price.
     */
    double originalBuyingPrice();
}
