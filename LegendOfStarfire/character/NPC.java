package character;

import java.util.ArrayList;

import stat.Stat;
import character.Character;
import attack.Attack;
import level.ExperiencePoints;

/**
 * Base NPC class - all special NPCs must be a subclass of this class
 * @author zellman01
*/
public abstract class NPC extends Character {
	private ExperiencePoints exp;
	
	/**
	 * Creates an NPC
	 * @param statArray The normalized Stat array from StatFactory
	 * @param name The name of the NPC
	 * @deprecated Use the one that contains the EXP modifier as well
	*/
	@Deprecated
	public NPC(Stat[] statArray, String name) {
		super(statArray, name);
	}
	
	/**
	 * Creates an NPC
	 * @param statArray The normalized Stat array from StatFactory
	 * @param name The name of the NPC
	 * @param exp The amount of EXP awarded after defeating the NPC
	*/
	public NPC(Stat[] statArray, String name, int exp) {
		super(statArray, name);
		this.exp = new ExperiencePoints(exp);
	}
	
	/**
	 * Selects an attack to use (AI only)
	 * @return The Attack object selected to make the attack with
	*/
	public abstract Attack makeAttack();
}
