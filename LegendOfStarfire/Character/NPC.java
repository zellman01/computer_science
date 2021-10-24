package character;

import stat.Stat;
import character.Character;

/**
 * Base NPC class - all special NPCs must be a subclass of this class
 * @author zellman01
*/
public abstract class NPC extends Character {
	public NPC(Stat[] statArray, String name) {
		super(statArray, name);
	}
	
	public abstract int damage();
}
