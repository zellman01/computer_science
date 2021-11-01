package inventory;

/**
 * Any object able to be held in the inventory must extend this.
 * @author zellman01
*/
public class GameObject {
	private boolean isKeyItem;
	
	/**
	 * Creates a basic GameObject to use in the inventory
	*/
	public GameObject() {
		isKeyItem = false;
	}
	
	/**
	 * Creates a GameObject object
	 * @param isKeyItem Is the GameObject a key item for the story or not
	*/
	public GameObject(boolean isKeyItem) {
		isKeyItem = true;
	}
	
	/**
	 * Gets if the GameObject is a key item
	 * @return The key item status of the GameObject
	*/
	public boolean isKeyItem() {
		return isKeyItem;
	}
}
