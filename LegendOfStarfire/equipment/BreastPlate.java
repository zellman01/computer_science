package equipment;

import equipment.Equipment;
import stat.StatName;

/**
 * Higher level of equipment - Breastplate
 * @author zellman01
*/
public class BreastPlate extends Equipment {
	
	/**
	 * Creates a BreastPlate
	 * @param name The name of the equipment
	 * @param amountChanged How much the stat of the equipment changes
	*/
	public BreastPlate(String name, int amountChanged) {
		super(name, StatName.DEF, amountChanged);
	}
}
