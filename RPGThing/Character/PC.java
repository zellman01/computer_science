package Character;

import Stat.Stat;
import Character.Character;
import Inventory.Inventory;

public class PC extends Character {
	private Inventory inventory;
	
	public PC(Stat[] statArray, String name) {
		super (statArray, name);
		inventory = new Inventory(15);
	}
}
