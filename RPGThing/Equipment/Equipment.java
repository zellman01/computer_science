package Equipment;

import Stat.StatName;

public class Equipment {
	private StatName statModified;
	private int amountModified;
	
	public Equipment(StatName stat, int amount) {
		statModified = stat;
		amountModified = amount;
	}
	
	public StatName getStatName() {
		return this.statModified;
	}
	
	public int getAmountModified() {
		return this.amountModified;
	}
}