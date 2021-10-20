package Equipment;

import Equipment.Equipment;
import Stat.StatName;

public class BreastPlate extends Equipment {
	private String name;
	
	public BreastPlate(String name, int amountChanged) {
		super(StatName.DEF, amountChanged);
		this.name = name;
	}
}