package Stat;

public enum StatName {
	HP("HP"),
	ATK("Attack"),
	DEF("Defense"),
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
