package Inventory;

import Inventory.GameObject;
import Inventory.InventoryBlock;

/**
 * The Inventory object
*/
public class Inventory {
	private int inventorySize;
	private int amountInInventory;
	private InventoryBlock storage[];
	
	/**
	 * Creates an inventory to an initial state
	 * @param amount The amount of storage to originally have
	*/
	public Inventory(int amount) {
		inventorySize = amount;
		amountInInventory = 0;
		storage = new InventoryBlock[inventorySize];
	}
	
	/**
	 * Adds an item to the inventory
	 * @param obj The obejct to try to add
	 * @return true if successful, false otherwise.
	*/
	public boolean addItem(GameObject obj) {
		return false;
	}
	
	/**
	 * Removes an item from a given slot, and returns the item.
	 * @param slot The slot to get the item from
	 * @return The GameObject if there was one there, or null otherwise
	*/
	public GameObject removeItem(int slot) {
		return null;
	}
	
	/**
	 * Increases the size of the Inventory.
	 * @param amount The amount to raise the inventory by.
	*/
	public void increaseSize(int amount) {
		int tempSize = inventorySize+amount;
		InventoryBlock temp[] = new InventoryBlock[tempSize];
		for (int i = 0; i < amountInInventory; i++) {
			temp[i] = storage[i];
		}
		
		inventorySize = tempSize;
		storage = temp;
	}
}
