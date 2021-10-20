package Stat;

import Stat.StatName;

public class Stat {
	private StatName name;
	private int amount;
	
	public Stat(StatName name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public Stat() {}
	
	@Override
	public String toString() {
		return "Stat name: " + name + "  Amount: " + amount;
	}
}