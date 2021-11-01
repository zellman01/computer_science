package inventory;

import java.util.Optional;

import inventory.GameObject;
import inventory.InventoryBlock;

/**
 * The Inventory object
 * @author zellman01
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
		for (int i = 0; i < inventorySize; i++) {
			storage[i] = new InventoryBlock();
		}
	}
	
	/**
	 * Adds an item to the inventory
	 * @param obj The obejct to try to add
	 * @return true if successful, false otherwise (inventory is full).
	*/
	public boolean addItem(GameObject obj) {
		if (amountInInventory == inventorySize) return false;
		for (int i = 0; i < inventorySize; i++) {
			if (storage[i].addItem(obj)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes an item from a given slot, and returns the item.
	 * @param slot The slot to get the item from (from 1 - inventory max)
	 * @return The GameObject if there was one there, or null otherwise
	*/
	public Optional<GameObject> removeItem(int slot) {
		slot--;
		if (slot > inventorySize || slot < 0) return Optional.empty();
		Optional<GameObject> retValue = storage[slot].removeItem();
		if (retValue.isPresent()) amountInInventory--;
		return retValue;
	}
	
	/**
	 * Increases the size of the Inventory.
	 * @param amount The amount to raise the inventory by.
	*/
	public void increaseSize(int amount) {
		int tempSize = inventorySize+amount;
		InventoryBlock temp[] = new InventoryBlock[tempSize];
		for (int i = 0; i < tempSize; i++) {
			temp[i] = new InventoryBlock();
		}
		
		for (int i = 0; i < amountInInventory; i++) {
			temp[i] = storage[i];
		}
		
		inventorySize = tempSize;
		storage = temp;
	}
}
