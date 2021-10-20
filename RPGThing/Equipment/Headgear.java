package Equipment;

import Equipment.Equipment;
import Stat.StatName;

public class Headgear extends Equipment {
	private String name;
	
	public Headgear(String name, int amountModified) {
		super(StatName.DEF, amountModified);
		this.name = name;
	}
}