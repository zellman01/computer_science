package character;

import java.util.Optional;

import stat.Stat;
import stat.StatName;
import equipment.EquipmentPage;
import equipment.Weapon;
import equipment.Headgear;
import equipment.BreastPlate;

/**
 * The parent class of PC and NPC.
 * @author zellman01
*/
public abstract class Character {
	private Stat[] statArray;
	private String name;
	private EquipmentPage ep;
	
	/**
	 * Create a Character object
	 * @param statArray a normalized array of the stats in the game
	 * @param name The character's name
	*/
	public Character(Stat[] statArray, String name) {
		this.statArray = statArray;
		this.name = name;
		ep = new EquipmentPage();
	}
	
	private String listStats() {
		String str = "\n\t";
		
		for (int i = 0; i < statArray.length; i++) {
			str += statArray[i] + " ";
			if (!ep.isEmpty()) {
				if (ep.getWeapon().isPresent() && statArray[i].getStatName() == ep.getWeapon().get().getStatName()) {
					int amount = ep.getWeapon().get().getAmountModified()+statArray[i].getStatAmount();
					str += " Amount: " + amount;
				} else if (ep.getHeadgear().isPresent() && statArray[i].getStatName() == ep.getHeadgear().get().getStatName()) {
					int amount = ep.getHeadgear().get().getAmountModified()+statArray[i].getStatAmount();
					str += " Amount: " + amount;
				} else if (ep.getBreastPlate().isPresent() && statArray[i].getStatName() == ep.getBreastPlate().get().getStatName()) {
					int amount = ep.getBreastPlate().get().getAmountModified()+statArray[i].getStatAmount();
					str += " Amount: " + amount;
				} else {
					str += "Amount: " + statArray[i].getStatAmount();
				}
			} else {
				str += "Amount: " + statArray[i].getStatAmount();
			}
			if (i != statArray.length-1) str += "\n";
			str += "\t";
		}
		
		return str;
	}
	
	@Override
	public String toString() {
		return "Character's name: " + name + "\n"
		+ "Stats: " + listStats() + "\n"
		+ ep;
	}
	
	/**
	 * Equips a weapon to the Character
	 * @param w The weapon to add
	 * @return True if successful, false otherwise
	*/
	public boolean addWeapon(Weapon w) {
		return ep.equipWeapon(w);
	}
	
	/**
	 * Removes a weapon, if one was equipped
	 * @return An Optional object containing a Weapon or null
	*/
	public Optional<Weapon> removeWeapon() {
		return ep.unEquipWeapon();
	}
	
	/**
	 * Obtains the currently equipped weapon
	 * @return An Optional object containing a Weapon or null
	*/
	public Optional<Weapon> getWeapon() {
		return ep.getWeapon();
	}
	
	/**
	 * Equips a Headgear to the Character
	 * @param hg The Headgear to add
	 * @return True if successful, false otherwise
	*/
	public boolean addHeadgear(Headgear hg) {
		return ep.equipHeadgear(hg);
	}
	
	/**
	 * Removes the Headgear, if one was equipped
	 * @return An Optional object containing a Headgear or null
	*/
	public Optional<Headgear> removeHeadgear() {
		return ep.unEquipHeadgear();
	}
	
	/**
	 * Obtains the currently equipped Headgear
	 * @return An Optional object containing a Headgear or null
	*/
	public Optional<Headgear> getHeadgear() {
		return ep.getHeadgear();
	}
	
	/**
	 * Equips a BreastPlate to the Character
	 * @param bp The BreastPlate to add
	 * @return True if successful, false otherwise
	*/
	public boolean addBreastPlate(BreastPlate bp) {
		return ep.equipBreastPlate(bp);
	}
	
	/**
	 *
	*/
	public Optional<BreastPlate> removeBreastplate() {
		return ep.unEquipBreastplate();
	}
	
	/**
	 *
	*/
	public Optional<BreastPlate> getBreastPlate() {
		return ep.getBreastPlate();
	}
	
	/**
	 * Gets the health stat to use
	 * @return The health Stat object
	*/
	public Stat getHealthStat() {
		return statArray[0];
	}
	
	/**
	 * Gets the attack stat to use
	 * @return The attack Stat object
	*/
	public Stat getAttackStat() {
		return statArray[1];
	}
	
	/**
	 * Gets the defense stat to use
	 * @return The defense Stat object
	*/
	public Stat getDefenseStat() {
		return statArray[2];
	}
	
	/**
	 *
	*/
	public void tookDamage(int amount) {
		statArray[0].raiseAmount(-amount);
	}
}
