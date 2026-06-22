package game.blacksmith;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.itemupgrade.ItemUpgrade;
/**
 * Class representing the UpgradeAction.
 * Created by:
 * @author Ngo Tran Ngoc Duy
 * @version 1.0
 */
public class UpgradeAction extends Action {
    /**
     * Amount
     */
    double amount ;
    /**
     * ItemUpgrade class
     */
    ItemUpgrade itemUpgrade;
    /**
     * Constructor for UpgradeAction.
     */
    public UpgradeAction(ItemUpgrade itemUpgrade) {
        this.itemUpgrade = itemUpgrade;
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
        this.amount = itemUpgrade.returnUpgrading(actor);
        if (amount == 0) {
            return actor + " can't ask for an upgrade for " + this.itemUpgrade;
        }
        actor.deductBalance((int) amount);
        return actor + " upgrade " + this.itemUpgrade;
    }
    /**
     * Get the menu description for the buying action.
     *
     * @param actor The actor performing the action.
     * @return A menu description for the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " upgrade " + this.itemUpgrade + " with the price " + this.itemUpgrade.originalUpgrade()  + " Runes";
    }
}
