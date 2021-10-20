package Character;

import Stat.Stat;
import Character.Character;

/**
 * Base NPC class - all special NPCs must be a subclass of this class
 * @author zellman01
*/
public class NPC extends Character {
	public NPC(Stat[] statArray, String name) {
		super(statArray, name);
	}
}