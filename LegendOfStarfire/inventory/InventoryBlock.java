package inventory;

import java.util.Optional;

import inventory.GameObject;
import equipment.Equipment;
import item.Item;

/**
 * The spaces of the character's Inventory
 * @author zellman01
*/
public class InventoryBlock {
	private GameObject invObject;
	private int amount;
	
	/**
	 * Creates a block already filled with an object.
	 * @param invObject the game object to fill the block with
	 * @param amount The amount that the inventory object has (should only be used with stackable items)
	*/
	public InventoryBlock(GameObject invObject, int amount) {
		this.invObject = invObject;
		this.amount = amount;
	}
	
	/**
	 * Blank constructor to create blocks ahead of time
	*/
	public InventoryBlock() {
		invObject = null;
		amount = 0;
	}
	
	/**
	 * Gets the GameObject held in this inventory slot, then removes it from the inventory. Typecasting required with this method.
	 * @return The GameObject if it exists, or null if it does not.
	*/
	public Optional<GameObject> removeItem() {
		GameObject temp = invObject;
		if (amount > 1) amount--;
		else {
			invObject = null;
			amount = 0;
		}
		return Optional.ofNullable(temp);
	}
	
	/**
	 * Adds a GameObject to the InventoryBlock as long as it does not have a item already in it.
	 * @param obj The GameObject trying to be added into this InventoryBlock
	 * @return true if it succeeds (no item already exists), false if it does not (item already exists)
	*/
	public boolean addItem(GameObject obj) {
		boolean retValue = true; // True if there is no item, and/or was added, or false otherwise
		if (Optional.ofNullable(invObject).isPresent() && invObject.equals(obj)) {
			if (obj instanceof Item) {
				Item temp = (Item)obj;
				if (amount < temp.getStackLength()) {
					amount++;
				} else retValue = false;
			} else retValue = false;
		} else if (Optional.ofNullable(invObject).isPresent()) retValue = false;
		if (retValue) invObject = obj;
		return retValue;
	}
	
	/**
	 * Obtains the GameItem stored in this InventoryBlock
	 * @return An Optional with the GameObject or null
	*/
	public Optional<GameObject> getItem() {
		return Optional.ofNullable(invObject);
	}
}
