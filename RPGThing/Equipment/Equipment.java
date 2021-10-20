package Equipment;

import Stat.StatName;

/**
 * Parent class of all Equipments
 * @author zellman01
*/
public class Equipment {
	private StatName statModified;
	private int amountModified;
	
	/**
	 * Creates an Equipment. Always used with super()
	 * @param stat The enum StatName of the stat that is modified
	 * @param amount The amount that the stat gets changed
	*/
	public Equipment(StatName stat, int amount) {
		statModified = stat;
		amountModified = amount;
	}
	
	/**
	 * Returns the enum identifier of StatName
	 * @return Enum identifier of StatName that the equipment modifies
	*/
	public StatName getStatName() {
		return this.statModified;
	}
	
	/**
	 * Returns amount changed with the stat
	 * @return Amount modified of the given stat
	*/
	public int getAmountModified() {
		return this.amountModified;
	}
}