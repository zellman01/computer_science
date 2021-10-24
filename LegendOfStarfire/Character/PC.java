package character;

import java.util.Optional;

import stat.Stat;
import character.Character;
import inventory.Inventory;
import inventory.GameObject;

/**
 * The main player class - most methods are wrapper methods to use already created methods for functionality
*/
public class PC extends Character {
	private Inventory inventory;
	
	public PC(Stat[] statArray, String name) {
		super (statArray, name);
		inventory = new Inventory(15);
	}
	
	public boolean addItem(GameObject go) {
		return inventory.addItem(go);
	}
	
	public Optional<GameObject> removeItem(int slot) {
		return inventory.removeItem(slot);
	}
}
