package item;

import item.Item;

/**
 * General potion class - Too general, so split up more
*/
public abstract class Potion extends Item {
	public Potion(String name) {
		super(name, false);
	}
	
	public Potion(String name, String desc) {
		super(name, desc, false);
	}
	
	public Potion(String name, boolean isKeyItem) {
		super(name, isKeyItem);
	}
	
	public Potion(String name, String desc, boolean isKeyItem) {
		super(name, desc, isKeyItem);
	}
}