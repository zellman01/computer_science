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
	 * Gets the stat value
	 * @return The amount the given stat has
	*/
	public int getStatAmount() { return amount; }
	
	/**
	 * Stat modifier function - uses raise and lower functions
	 * @param amount The amount to modify the stat
	 * @param healing True if healing, false otherwise
	*/
	public void modifyStat(int amount, boolean healing) {
		if (amount == 0) return;
		if (healing && amount > 0) raiseAmount(amount);
		else if (!healing && amount < 0) lowerAmount(amount);
	}
	
	private void raiseAmount(int amount) {
		if (amount < 0) return;
		this.amount += amount;
	}
	
	private void lowerAmount(int amount) {
		if (amount > 0) return;
		this.amount -= amount;
		if (name.equals(StatName.HP) && amount < 0) amount = 0; 
	}
	
	@Override
	public String toString() {
		return "Stat name: " + name;
	}
}
