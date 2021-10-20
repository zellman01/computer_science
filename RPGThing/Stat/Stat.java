package Stat;

import Stat.StatName;

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
	
	
	public Stat() {}
	
	@Override
	public String toString() {
		return "Stat name: " + name + "  Amount: " + amount;
	}
}