package game.item.itempurchase;

import edu.monash.fit2099.engine.actors.Actor;
/**
 * Class representing the ItemSellingPrice.
 * Created by:
 * @author Ngo Tran Ngoc Duy
 * @version 1.0
 */
public interface ItemSellingPrice {
    /**
     * Calculate the selling price for an item based on certain conditions.
     *
     * @param option The selling option.
     * @param actor  The actor selling the item.
     * @return The calculated selling price.
     */
    double returnSelling(boolean option, Actor actor);

    /**
     * Check if the original selling chance is valid.
     *
     * @return true if the original selling chance is valid, false otherwise.
     */
    boolean originalSellingChance();

    /**
     * Get the original selling price of an item.
     *
     * @return The original selling price.
     */
    double originalSellingPrice();
}
