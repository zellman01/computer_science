package stat;

import stat.StatName;

/**
 * Low level Stat class. Depends on StatName.
 * @author zellman01
*/
public class Stat {
	private StatName name;
	private int amount;
	
	/**
	 * Creates a Stat object to use
	 * @param name The StatName enum of the stat
	 * @param amount The amount that the stat starts with
	*/
	public Stat(StatName name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	/**
	 * Get the StatName of the Stat
	 * @return The enum identifier of StatName
	*/
	public StatName getStatName() { return name; }
	
	/**
	 * Gets the amount that the stat is modified by
	 * @return The amount to modify the given stat by
	*/
	public int getStatAmount() { return amount; }
	
	/**
	 * Raises/lowers a stat by a given amount. A stat cannot be lower than 0.
	 * @param amount Positive int for raising, negative int for lowering.
	*/
	public void raiseAmount(int amount) {
		this.amount -= amount;
		if (this.amount < 0) this.amount = 0;
	}
	
	/**
	 * Creates an empty Stat object
	 * @deprecated Unsure if used anywhere in the project
	*/
	@Deprecated
	public Stat() {}
	
	@Override
	public String toString() {
		return "Stat name: " + name;
	}
}
