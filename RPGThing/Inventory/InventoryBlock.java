package Inventory;

import java.util.Optional;

import Inventory.GameObject;
import Equipment.Equipment;

/**
 * The spaces of the character's Inventory
 * @author zellman01
*/
public class InventoryBlock {
	private GameObject invObject;
	
	/**
	 * Creates a block already filled with an object.
	 * @param invObject the game object to fill the block with
	*/
	public InventoryBlock(GameObject invObject) {
		this.invObject = invObject;
	}
	
	/**
	 * Blank constructor to create blocks ahead of time
	*/
	public InventoryBlock() {}
	
	/**
	 * Gets the GameObject held in this inventory slot, then removes it from the inventory. Typecasting required with this method.
	 * @return The GameObject if it exists, or null if it does not.
	*/
	public Optional<GameObject> removeItem() {
		if (invObject == null) return Optional.empty();
		GameObject temp = invObject;
		invObject = null;
		return Optional.of(temp);
	}
	
	/**
	 * Adds a GameObject to the InventoryBlock as long as it does not have a item already in it.
	 * @param obj The GameObject trying to be added into this InventoryBlock
	 * @return true if it succeeds (no item already exists), false if it does not (item already exists)
	*/
	public boolean addItem(GameObject obj) {
		if (invObject != null) return false;
		invObject = obj;
		return true;
	}
}
