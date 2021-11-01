package character;

import stat.Stat;
import character.Character;
import attack.Attack;

/**
 * Base NPC class - all special NPCs must be a subclass of this class
 * @author zellman01
*/
public abstract class NPC extends Character {
	private Attack attackArray[];
	
	/**
	 * Creates an NPC
	 * @param statArray The normalized Stat array from StatFactory
	 * @param name The name of the NPC
	*/
	public NPC(Stat[] statArray, String name) {
		super(statArray, name);
	}
}
