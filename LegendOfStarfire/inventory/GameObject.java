package inventory;

/**
 * Any object able to be held in the inventory must extend this.
 * @author zellman01
*/
public class GameObject {
	private boolean isKeyItem;
	protected String name;
	
	/**
	 * Creates a basic GameObject to use in the inventory
	 * @param name The name of the GameObject
	*/
	public GameObject(String name) {
		this.name = name;
		isKeyItem = false;
	}
	
	/**
	 * Creates a GameObject object
	 * @param name The name of the GameObject
	 * @param isKeyItem Is the GameObject a key item for the story or not
	*/
	public GameObject(String name, boolean isKeyItem) {
		this.name = name;
		this.isKeyItem = isKeyItem;
	}
	
	/**
	 * Gets if the GameObject is a key item
	 * @return The key item status of the GameObject
	*/
	public boolean isKeyItem() {
		return isKeyItem;
	}
	
	/**
	 * Gets the GameObject name
	 * @return A string that is the name of the GameObject
	*/
	public String getName() { return name; }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if (!(obj instanceof GameObject)) return false;
		
		GameObject gObj = (GameObject)obj;
		
		return name.equals(gObj.getName());
	}
}
