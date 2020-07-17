package game.bom.primary;

/**
 * Primary character class
 * @author Admin
 * @version 0.0.1
 * @since 0.0.1
 */
public class Player {
	private Mana manaPool;
	private String name;
	private int health;
	
	public Player(int mana, String name) {
		manaPool = new Mana(mana, 0, 0);
		this.name = name;
		health = 30;
	}
	
	public int remainingMana() {
		return getManaPool().retrieveCharacterManaAmount();
	}
	
	public void lockMana(int amount) { getManaPool().lockMana(amount); }
	
	public void usedMana(int amount) { getManaPool().useMana(amount); }
	
	public void turnEnd(int manaRecovered) { 
		getManaPool().recoverMana(manaRecovered);
		getManaPool().addTemp(-getManaPool().totalTemp()); // Remove any temporary mana
	}
	
	public void addTempMana(int amount) {
		getManaPool().addTemp(amount);
	}
	
	public Mana getManaPool() { return manaPool; }
	public String getName() { return name; }
	public int getHealth() { return health; }
	
	public String toString() {
		String str = "";
		str += "Name: " + getName();
		str += "\nMana remaining: " + remainingMana();
		str += "\nHealth: " + getHealth();
		return str;
	}
}
