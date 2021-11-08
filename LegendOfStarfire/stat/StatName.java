package stat;

/**
 * Enumeration of the stat names, to make it more uniform
 * @author zellman01
*/
public enum StatName {
	/**
	 * The HP stat enumeration
	*/
	HP("HP", 1),
	/**
	 * The Attack stat enumeration
	*/
	ATK("Attack", 2),
	/**
	 * The Defense stat enumeration
	*/
	DEF("Defense", 3),
	/**
	 * The Speed stat enumeration
	*/
	SPD("Speed", 4),
	/**
	 * The max HP a character may have
	*/
	MAXHP("Maximum HP", 0);
	
	private final String name;
	private final int statArrayPos;
	
	private StatName(String name, int statArrayPos) {
		this.name = name;
		this.statArrayPos = statArrayPos;
	}
	
	/**
	 * Gets the position of the standarized array of the stat
	 * @return Position of standardized array
	*/
	public int getArrayPos() { return statArrayPos; }
	
	@Override
	public String toString() {
		return this.name;
	}
}
