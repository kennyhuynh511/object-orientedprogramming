package game.item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.item.itemaction.Consumable;
import game.item.itemaction.ConsumableAction;

/**
 * Class representing the Runes
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public class Runes extends Item implements Consumable {
    /**
     * Return the value of Runes
     * @return the value of Runes
     */
    public int getValue() {
        return value;
    }
    /**
     * The value of the Runes
     */
    private final int value;

    /***
     * Constructor.
     * @param value get the value of the Runes
     */
    public Runes(int value) {
        super("Runes", '$', true);
        this.value = value;
        this.addCapability(ItemStatus.CURRENCY);
        this.addCapability(ItemStatus.CAN_NOT_UPGRADE);
    }

    /**
     * Print the name of the runes class
     */
    @Override
    public String toString(){
        return "Runes";
    }

    /**
     * List of allowable actions that the item can perform to the current actor
     * @param owner the actor that owns the item
     * @return a list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionlist = new ActionList();
        actionlist.add(new ConsumableAction(this));
        return actionlist;
    }
    /**
     * @param actor actor class
     * Increase the wallet balance
     */
    @Override
    public void consume(Actor actor) {
        actor.addBalance(this.getValue());
        actor.removeItemFromInventory(this);
    }
}
