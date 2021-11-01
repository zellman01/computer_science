package character;

import java.util.Optional;

import stat.Stat;
import character.Character;
import inventory.Inventory;
import inventory.GameObject;
import attack.Attack;

/**
 * The main player class - most methods are wrapper methods to use already created methods for functionality
 * @author zellman01
*/
public class PC extends Character {
	private Inventory inventory;
	private Attack attackArray[];
	
	/**
	 * Creates a PlayerCharacter (PC) Object
	 * @param statArray A formalized stat array created by the StatFactory class
	 * @param name The name of the character
	*/
	public PC(Stat[] statArray, String name) {
		super (statArray, name);
		inventory = new Inventory(15);
	}
	
	/**
	 * Adds an item to the inventory
	 * @param go The GameObject object to add to the inventory
	 * @return True if successful, false otherwise
	*/
	public boolean addItem(GameObject go) {
		return inventory.addItem(go);
	}
	
	/**
	 * Removes an item from a given slot in the inventory and returns it
	 * @param slot The slot position to remove the item from (1 - inventory max)
	 * @return An Optional object containing either the GameObject removed, or null if nothing was removed
	*/
	public Optional<GameObject> removeItem(int slot) {
		return inventory.removeItem(slot);
	}
}
