package item;

import inventory.GameObject;
import character.Character;

/**
 * Master class of all useable items that can be stored in the inventory
*/
public abstract class Item extends GameObject {
	//private String name;
	private String description;
	private int stackLength; // How many of this item can be stacked on one another
	private boolean reuseable;
	
	/**
	 * Creates an Item object
	 * @param name The name of the item
	 * @param isKeyItem Is the item a key item for the story
	*/
	public Item(String name, boolean isKeyItem) {
		super(name, isKeyItem);
		//this.name = name;
		stackLength = 1;
		reuseable = false;
	}
	
	/**
	 * Creates an Item object
	 * @param name The name of the item
	 * @param stackLength How many of this item can be stacked on one another in the Inventory
	 * @param isKeyItem Is the item a key item for the story
	*/
	public Item(String name, int stackLength, boolean isKeyItem) {
		super(name, isKeyItem);
		//this.name = name;
		this.stackLength = stackLength;
		reuseable = false;
	}
	
	/**
	 * Creates an Item object
	 * @param name The name of the item
	 * @param desc The description of the item
	 * @param isKeyItem Is the item a key item for the story
	*/
	public Item(String name, String desc, boolean isKeyItem) {
		super(name, isKeyItem);
		//this.name = name;
		description = desc;
		stackLength = 1;
		reuseable = false;
	}
	
	/**
	 * Creates an Item object
	 * @param name The name of the item
	 * @param desc The description of the item
	 * @param stackLength How many of this item can be stacked on one another in the Inventory
	 * @param isKeyItem Is the item a key item for the story
	*/
	public Item(String name, String desc, int stackLength, boolean isKeyItem) {
		super(name, isKeyItem);
		//this.name = name;
		description = desc;
		this.stackLength = stackLength;
		reuseable = false;
	}
	
	
	/**
	 * Gets the amount that can be stacked
	 * @return The stack length
	*/
	public int getStackLength() { return stackLength; }
	
	/**
	 * Marks something as reuseable. Will not umark something as useable if already marked.
	*/
	public void markReusuable() {
		reuseable = true;
	}
	
	/**
	 * Gets the reuseable flag status
	 * @return If the item is reuseable
	*/
	public boolean isReuseable() { return reuseable; }
	
	/**
	 * Does an effect specific to the more specific type of item.
	 * @param user The Character object that has used it
	*/
	public abstract void doEffect(Character user);
}