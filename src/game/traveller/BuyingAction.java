package game.traveller;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.itempurchase.ItemBuyingPrice;
/**
 * Class representing the BuyingAction
 * Created by:
 * @author Ngo Tran Ngoc Duy
 * @version 1.0
 */
public class BuyingAction extends Action {
    /**
     * ItemBuyingPrice class
     */
    ItemBuyingPrice itemBuyingPrice;
    /**
     * Constructor for BuyingAction.
     */
    public BuyingAction(ItemBuyingPrice itemBuyingPrice) {

        this.itemBuyingPrice = itemBuyingPrice;
    }
    /**
     * Execute the buying action by updating the actor's balance and returning a description.
     *
     * @param actor The actor performing the action.
     * @param map The game map.
     * @return A description of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        double amount = itemBuyingPrice.returnBuying(itemBuyingPrice.originalBuyingChance(), actor);
        actor.addBalance((int) amount);
        return actor + " sells " + this.itemBuyingPrice + " with a price " + amount;
    }
    /**
     * Get the menu description for the buying action.
     *
     * @param actor The actor performing the action.
     * @return A menu description for the action.
     */
    @Override
    public String menuDescription(Actor actor) {

        return actor + " sells " + this.itemBuyingPrice + " with the price " + this.itemBuyingPrice.originalBuyingPrice() + " Runes" + '\n';
    }
}
