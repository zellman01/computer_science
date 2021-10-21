package Equipment;

import Equipment.Equipment;
import Stat.StatName;

/**
 * Higher level of equipment - Headgear
 * @author zellman01
*/
public class Headgear extends Equipment {
	
	/**
	 * Creates a Headgear
	 * @param name The name of the equipment
	 * @param amountChanged How much the stat of the equipment changes
	*/
	public Headgear(String name, int amountChanged) {
		super(name, StatName.DEF, amountChanged, 0);
	}
}
