package game.traveller;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.itempurchase.ItemSellingPrice;
/**
 * Class representing the Selling Action.
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 */
public class SellingAction extends Action {
    /**
     * ItemSellingPrice class
     */
    ItemSellingPrice itemSellingPrice;
    /**
     * Constructor for SellingAction.
     * @param itemSellingPrice The ItemSellingPrice object representing the item's selling price.
     */
    public SellingAction(ItemSellingPrice itemSellingPrice) {

        this.itemSellingPrice = itemSellingPrice;
    }
    /**
     * Execute the selling action by updating the actor's balance and returning a description.
     *
     * @param actor The actor performing the action.
     * @param map The game map.
     * @return A description of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        double amount = itemSellingPrice.returnSelling(itemSellingPrice.originalSellingChance(), actor);
        if (amount == 0) {
            return actor + " can't buy " + this.itemSellingPrice;
        }
        actor.deductBalance((int) amount);
        return actor + " buys " + this.itemSellingPrice + " with a price " + amount;
    }
    /**
     * Get the menu description for the selling action.
     *
     * @param actor The actor performing the action.
     * @return A menu description for the action.
     */
    @Override
    public String menuDescription(Actor actor) {

        return actor + " buys " + this.itemSellingPrice + " with the price " + this.itemSellingPrice.originalSellingPrice() + " Runes";
    }
}
