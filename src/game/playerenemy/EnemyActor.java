package game.playerenemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.AttackAction;
import game.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for enemy actors
 * Modified by:
 * @author Nguyen Khang Huynh
 * @version 1.0
 * Modified by:
 * @author Fabian Wise
 * @version 1.0
 */
public abstract class EnemyActor extends Actor {
    /**
     * the hashtable of the behaviours
     */
    public List<Behaviour> behaviours = new ArrayList<>();
    /**
     * the int of the hit_rate
     */
    public int hitRate;
    /**
     * the int Wanderings damage
     */
    public int damage;
    /**
     * the string of enemy action
     */
    public String verb;
    /**
     * Constructor
     *
     * @param name    The name of the Enemy
     * @param displayChar The character of the Enemy
     * @param hitPoints   The hit points of the Enemy
     * @param verb The action of the enemy actor
     * @param hitRate The accuracy
     * @param damage The damange
     */
    public EnemyActor(String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
        super(name,displayChar,hitPoints);
        this.hitRate = hitRate;
        this.damage = damage;
        this.verb = verb;
        this.behaviours.add(new MonsterAttackBehaviour());
        this.behaviours.add(new WanderBehaviour());
    }
    /**
     * Constructor
     *
     * @param name    The name of the Enemy
     * @param displayChar The character of the Enemy
     * @param hitPoints   The hit points of the Enemy
     * @param verb The action of the enemy actor
     * @param hitRate The accuracy
     * @param damage The damange
     * @param followActor The actor class
     */
    public EnemyActor(Actor followActor, String name, char displayChar, int hitPoints,int hitRate, int damage, String verb) {
        super(name,displayChar,hitPoints);
        this.hitRate = hitRate;
        this.damage = damage;
        this.verb = verb;
        this.behaviours.add(new MonsterAttackBehaviour());
        this.behaviours.add(new game.playerenemy.FollowBehaviour(followActor));
        this.behaviours.add(new WanderBehaviour());
    }




    /**
     * Set the IntrinsicWeapon weapon
     * @return the damage, action verb and the hit rage
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(this.damage,this.verb, this.hitRate);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }



    /**
     * The EnemyActor can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the actions of the wandering Undead
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for(Item item: otherActor.getItemInventory()){
                if(item instanceof WeaponItem){
                    actions.add(new AttackAction(this,direction,(WeaponItem)item));
                }
            }
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
