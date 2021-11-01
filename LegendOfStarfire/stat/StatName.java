package stat;

/**
 * Enumeration of the stat names, to make it more uniform
 * @author zellman01
*/
public enum StatName {
	/**
	 * The HP stat enumeration
	*/
	HP("HP"),
	/**
	 * The Attack stat enumeration
	*/
	ATK("Attack"),
	/**
	 * The Defense stat enumeration
	*/
	DEF("Defense"),
	/**
	 * The Speed stat enumeration
	*/
	SPD("Speed");
	
	private final String name;
	
	private StatName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
