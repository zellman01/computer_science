package attack;

import java.util.ArrayList;

import attack.Attack;

/**
 * A common attack generator class, to keep memory usage down with too many Attack objects (all common attacks everything has is stored here)
 * @author zellman01
*/
public class CommonAttack {
	/** Array list for all Attacks every enemy/PC has */
	public static ArrayList<Attack> commonAttacks = new ArrayList<Attack>();
	public static int arrayLength = 0;
	
	/**
	 * Adds an attack that is labeled common attack - every enemy will have it
	 * @param attack The Attack object to consider a common attack
	*/
	public static void addAttack(Attack attack) {
		commonAttacks.add(attack);
		arrayLength++;
	}
}