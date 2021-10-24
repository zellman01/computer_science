package equipment;

import equipment.*;

/**
 * Equipment pages to allow enemies and the player to have equipment
 * @author zellman01
*/
public class EquipmentPage {
	private final int TOTALEQUIPPED = 1;
	private Headgear headGear;
	private BreastPlate breastPlate;
	private Weapon weapon;
	
	/**
	 * Creates a blank EquipmentPage
	*/
	public EquipmentPage() {
		
	}
	
	/**
	 * Equips a headgear, if one is not already equipped
	 * @param hg The headgear to try to equip
	 * @return If it was successful
	*/
	public boolean equipHeadgear(Headgear hg) {
		if (headGear == null && hg != null) {
			headGear = hg;
			return true;
		} // Else don't equip - Something else is already there (Same for the other two methods)
		return false;
	}
	
	/**
	 * Equips a breastplate, if one is not already equipped
	 * @param bp The breastplate to try to equip
	 * @return If it was successful
	*/
	public boolean equipBreastPlate(BreastPlate bp) {
		if (breastPlate == null && bp != null) {
			breastPlate = bp;
			return true;
		}
		return false;
	}
	
	/**
	 * Equips a weapon, if one is not already equipped
	 * @param w The weapon to try to equip
	 * @return If it was successful
	*/
	public boolean equipWeapon(Weapon w) {
		if (weapon == null && w != null) {
			weapon = w;
			return true;
		}
		return false;
	}
	
	/**
	 * Remove the headgear equipped
	 * @return The headgear that was unequipped to put back in inventory (can return null)
	*/
	public Headgear unEquipHeadgear() {
		Headgear temp = headGear;
		headGear = null;
		return temp;
	}
	
	/**
	 * Remove the breastplate equipped
	 * @return The breastplate that was unequipped to put back in inventory (can return null)
	*/
	public BreastPlate unEquipBreastplate() {
		BreastPlate temp = breastPlate;
		breastPlate = null;
		return temp;
	}
	
	/**
	 * Remove the weapon equipped
	 * @return The weapon that was unequipped to put back in inventory (can return null)
	*/
	public Weapon unEquipWeapon() {
		Weapon temp = weapon;
		weapon = null;
		return temp;
	}
	
	/**
	 * Checks if the EquipmentPage is entirely empty
	 * @return True if the headgear, breastplate and weapon are null (none equipped)
	*/
	public boolean isEmpty() {
		return (headGear == null) && (breastPlate == null) && (weapon == null);
	}
	
	public Headgear getHeadgear() { return headGear; }
	
	public BreastPlate getBreastPlate() { return breastPlate; }
	
	public Weapon getWeapon() { return weapon; }
	
	@Override
	public String toString() {
		String str = "Equipment:\n\t";
		if (isEmpty()) str += "None equipped.";
		else {
			if (headGear != null) str += headGear + "\n\t";
			if (breastPlate != null) str += breastPlate + "\n\t";
			if (weapon != null) str +=  weapon + "\n";
		}
		return str;
	}
}
