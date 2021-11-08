package equipment;

import java.util.Optional;

import stat.StatName;
import inventory.GameObject;

/**
 * Parent class of all Equipments
 * @author zellman01
*/
public abstract class Equipment extends GameObject {
	private StatName statModified;
	private int amountModified;
	private String name;
	
	/**
	 * Creates an Equipment. Always used with super()
	 * @param name The name of the equipment
	 * @param stat The enum StatName of the stat that is modified
	 * @param amount The amount that the stat gets changed
	*/
	public Equipment(String name, StatName stat, int amount) {
		this.name = name;
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
	
	/**
	 * Checks if the given Equipment is a Headgear object
	 * @return If the Equipment object is a Headgear object
	*/
	public boolean isHeadgear() { return this instanceof Headgear; }
	
	/**
	 * Checks if the given Equipment is a BreastPlate object
	 * @return If the Equipment object is a BreastPlate object
	*/
	public boolean isBreastplate() { return this instanceof BreastPlate; }
	
	/**
	 * Checks if the given Equipment is a Weapon object
	 * @return If the Equipment object is a Weapon object
	*/
	public boolean isWeapon() { return this instanceof Weapon; }
	
	/**
	 * Returns a HeadGear object, if obj is one.
	 * @param obj The game object to check
	 * @return An Optional object, containing a Headgear or null
	*/
	public static Optional<Headgear> returnHeadgear(GameObject obj) {
		return Optional.ofNullable((Headgear)(obj));
	}
	
	/**
	 * Returns a BreastPlate object, if obj is one.
	 * @param obj The game object to check
	 * @return An Optional object, containing a BreastPlate or null
	*/
	public static Optional<BreastPlate> returnBreastplate(GameObject obj) {
		return Optional.ofNullable((BreastPlate)(obj));
	}
	
	/**
	 * Returns a Weapon object, if obj is one.
	 * @param obj The game object to check
	 * @return An Optional object, containing a Weapon or null
	*/
	public static Optional<Weapon> returnWeapon(GameObject obj) {
		return Optional.ofNullable((Weapon)(obj));
	}
	
	@Override
	public String toString() {
		String str = "";
		if (isHeadgear()) str += "Headgear: ";
		if (isBreastplate()) str += "Breastplate: ";
		if (isWeapon()) str += "Weapon:\n";
		str += "\t\tName: " + name + "\n\t\tStat changed: " + statModified + "\n\t\tAmount: " + amountModified + "\n";
		return str;
	}
}
