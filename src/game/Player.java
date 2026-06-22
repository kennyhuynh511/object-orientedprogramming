package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.ground.DoubleGate.DoubleGate;
import game.ground.burialgroundgate.BurialGroundGate;
import game.item.Runes;

import java.util.Iterator;
import java.util.List;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 *
 */
public class Player extends Actor implements ActorRespawn {
    /**
     * The first map that player are spawned on
     */
    Location spawnLocation;
    /**
     * Set user stamina to 200*/
    private final int player_stamina = 200;

    /**
     * Constructor.
     * @param location the location that the player spawned
     */
    public Player(Location location) {
        super("The Abstracted One", '@', 150);
        this.spawnLocation = location;
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.ALLOWABLE_TO_ENTER); // let the actor enter some specific place
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(this.player_stamina));
        this.addCapability(Status.CAN_NOT_RESET);
    }
    /**
     * Display and set the stamina of the player actor after every turn increase by 1% and display the hit points
     * Display the player currency
     * @param actions Action class
     * @param display Display class
     * @param map  map class
     * @param lastAction Action class
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        double increaseStamina = (this.player_stamina*0.01);
        this.modifyAttribute(BaseActorAttributes.STAMINA,ActorAttributeOperations.INCREASE,(int)increaseStamina);
        display.println(this + " has stamina " + this.getAttribute(BaseActorAttributes.STAMINA) + " and hit points " + this.getAttribute(BaseActorAttributes.HEALTH));
        display.println(this + " has the wallet balance: " + this.getBalance());
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null){
            return lastAction.getNextAction();}

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Add an item to this Player inventory.
     * @param item The Item to add.
     */
    @Override
    public void addItemToInventory(Item item) {
        super.addItemToInventory(item);
    }
    /**
     * Remove an item from this player's inventory.
     * Although it is possible to return the itemInventory to be modified by the client directly,
     * it is not done so to prevent privacy leak.
     * @param item The Item to remove.
     */
    @Override
    public void removeItemFromInventory(Item item) {
        super.removeItemFromInventory(item);
    }

    @Override
    public void addBalance(int amount) {
        super.addBalance(amount);
    }
    /**
     * A method for deducting the balance of the Actor's wallet.
     * Although this can be handled by the Wallet class itself, the prevention of privacy leak is prioritised.
     * @param amount the amount to be deducted from the balance
     */
    public void deductBalance(int amount) {
        super.deductBalance(amount);
    }

    /**
     * Method that can be executed when the player is unconscious due to the action of another actor
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        map.locationOf(this).addItem(new Runes(this.getBalance()));
        this.resetMap(map);
        super.unconscious(actor,map);
        this.respawn();
        return "";
    }

    /**
     * Method that can be executed when the player is unconscious due to natural causes or accident
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(GameMap map) {
        map.locationOf(this).addItem(new Runes(this.getBalance()));
        this.resetMap(map);
        super.unconscious(map);
        this.respawn();
        return "";
    }
    /**
     * Method that reset the map
     * @param map the map
     */
    @Override
    public void resetMap(GameMap map) {
        NumberRange x = map.getXRange();
        NumberRange y = map.getYRange();
        Iterator<Integer> iter_y = y.iterator();
        // remove spawned enemies from the map
        while (iter_y.hasNext()) {
            Iterator<Integer> iter_x = x.iterator();
            int current_iter_y;
            int current_iter_x;
            current_iter_y = iter_y.next();
            while (iter_x.hasNext()) {
                current_iter_x = iter_x.next();
                Actor actor = map.getActorAt(map.at(current_iter_x, current_iter_y));
                if (actor != null) {
                    if (!(actor.hasCapability(Status.CAN_NOT_RESET))){
                        actor.unconscious(map);
                    } else if (actor.hasCapability(Status.BOSS)) {
                        int maxHealth = actor.getAttributeMaximum(BaseActorAttributes.HEALTH);
                        int currentHealth = actor.getAttribute(BaseActorAttributes.HEALTH);
                        int deltaHeal = maxHealth - currentHealth;
                        actor.heal(deltaHeal);
                    }
                } if (map.at(current_iter_x, current_iter_y).getGround() != null) {
                    Ground current_ground = map.at(current_iter_x, current_iter_y).getGround();
                    if (current_ground instanceof DoubleGate) { // check for Gate
                        //current_ground;
                        ((DoubleGate) current_ground).setGetStatus(true);
                    } else if (current_ground instanceof BurialGroundGate) {
                        ((BurialGroundGate) current_ground).setGetStatus(true);
                    }
                } if(map.at(current_iter_x, current_iter_y).getItems() != null) {
                    List<Item> items = map.at(current_iter_x, current_iter_y).getItems();
                    Iterator<Item> items_iter = items.iterator();
                    while (items_iter.hasNext() && items.size() > 0) {
                        Item current_item = items_iter.next();
                        if (current_item instanceof Runes) {
                            map.at(current_iter_x, current_iter_y).removeItem(current_item);
                        }
                    }
                }
            }
        }
    }
    /**
     * Method that respawn the player
     */
    @Override
    public void respawn() {
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        this.deductBalance(this.getBalance());
        this.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE,this.getAttributeMaximum(BaseActorAttributes.HEALTH));
        this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE,this.getAttributeMaximum(BaseActorAttributes.STAMINA));
        spawnLocation.addActor(this);
        for (String line : FancyMessage.A_DREAM.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        spawnLocation.map().draw(new Display());
    }
}


