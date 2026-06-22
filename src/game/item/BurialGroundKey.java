package game.item;

import edu.monash.fit2099.engine.items.Item;

/**
 * Class representing the Burial Ground Key
 * Created by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public class BurialGroundKey extends Item {
    /***
     * Constructor.
     */
    public BurialGroundKey() {

        super("Old Key", '-', true);
        this.addCapability(ItemStatus.UNLOCK_GATE);
        this.addCapability(ItemStatus.CAN_NOT_UPGRADE);
    }
    /**
     * Print the name of the Key
     */
    @Override
    public String toString(){
        return "Old key";
    }

}
