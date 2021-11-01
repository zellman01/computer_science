package character;

import java.util.ArrayList;

import stat.Stat;
import character.Character;
import attack.Attack;

/**
 * Base NPC class - all special NPCs must be a subclass of this class
 * @author zellman01
*/
public abstract class NPC extends Character {
	private ArrayList<Attack> attackArray;
	
	/**
	 * Creates an NPC
	 * @param statArray The normalized Stat array from StatFactory
	 * @param name The name of the NPC
	*/
	public NPC(Stat[] statArray, String name) {
		super(statArray, name);
		attackArray = new ArrayList<Attack>();
	}
	
	/**
	 * Adds an attack to the AttackArray
	 * @param attack The Attack object to add
	 * @return True if adding succeeded.
	*/
	public boolean addAttack(Attack attack) {
		return attackArray.add(attack);
	}
	
	/**
	 * Selects an attack to use
	 * @return The Attack object selected to make the attack with
	*/
	public abstract Attack makeAttack();
}
