package Character;

import Stat.Stat;
import Character.Character;
import Equipment.EquipmentManager;

public class PC extends Character {
	private EquipmentManager em;
	
	public PC(Stat[] statArray, String name) {
		super (statArray, name);
		em = super;
	}
}