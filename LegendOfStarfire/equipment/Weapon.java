package equipment;

import equipment.Equipment;
import stat.StatName;

/**
 * Higher level of equipment - Weapon
 * @author zellman01
*/
public class Weapon extends Equipment {
	
	/**
	 * Creates a Weapon
	 * @param name The name of the equipment
	 * @param amountChanged How much the stat of the equipment changes
	*/
	public Weapon(String name, int amountChanged) {
		super(name, StatName.ATK, amountChanged);
	}
}
