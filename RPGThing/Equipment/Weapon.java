package Equipment;

import Equipment.Equipment;
import Stat.StatName;

public class Weapon extends Equipment {
	private String name;
	
	public Weapon(String name, int amountChanged) {
		super(StatName.ATK, amountChanged);
		this.name = name;
	}
}