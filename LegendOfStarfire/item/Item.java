package item;

import inventory.GameObject;
import character.Character;

/**
 * Master class of all useable items that can be stored in the inventory
*/
public abstract class Item extends GameObject {
	private String name;
	private String description;
	
	/**
	 * Creates an Item object
	 * @param name The name of the item
	 * @param isKeyItem Is the item a key item for the story
	*/
	public Item(String name, boolean isKeyItem) {
		super(isKeyItem);
		this.name = name;
	}
	
	/**
	 * Creates an Item object
	 * @param name The name of the item
	 * @param desc The description of the item
	 * @param isKeyItem Is the item a key item for the story
	*/
	public Item(String name, String desc, boolean isKeyItem) {
		super(isKeyItem);
		this.name = name;
		description = desc;
	}
	
	/**
	 * Does an effect specific to the more specific type of item.
	 * @param user The Character object that has used it
	*/
	public abstract void doEffect(Character user);
}