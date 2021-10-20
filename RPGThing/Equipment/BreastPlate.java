package Equipment;

import Equipment.Equipment;
import Stat.StatName;

/**
 * Higher level of equipment - Breastplate
 * @author zellman01
*/
public class BreastPlate extends Equipment {
	private String name;
	
	/**
	 * Creates a BreastPlate
	 * @param name The name of the equipment
	 * @param amountChanged How much the stat of the equipment changes
	*/
	public BreastPlate(String name, int amountChanged) {
		super(StatName.DEF, amountChanged);
		this.name = name;
	}
}