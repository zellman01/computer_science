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
				if (ep.getWeapon() != null && statArray[i].getStatName() == ep.getWeapon().getStatName()) {
					int amount = ep.getWeapon().getAmountModified()+statArray[i].getStatAmount();
					str += " Amount: " + amount;
				} else if (ep.getHeadgear() != null && statArray[i].getStatName() == ep.getHeadgear().getStatName()) {
					int amount = ep.getHeadgear().getAmountModified()+statArray[i].getStatAmount();
					str += " Amount: " + amount;
				} else if (ep.getBreastPlate() != null && statArray[i].getStatName() == ep.getBreastPlate().getStatName()) {
					int amount = ep.getBreastPlate().getAmountModified()+statArray[i].getStatAmount();
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
	
	public boolean addWeapon(Weapon w) {
		return ep.equipWeapon(w);
	}
	
	public Optional<Weapon> removeWeapon() {
		return Optional.ofNullable(ep.unEquipWeapon());
	}
	
	public boolean addHeadgear(Headgear hg) {
		return ep.equipHeadgear(hg);
	}
	
	public Optional<Headgear> removeHeadgear() {
		return Optional.ofNullable(ep.unEquipHeadgear());
	}
	
	public boolean addBreastPlate(BreastPlate bp) {
		return ep.equipBreastPlate(bp);
	}
	
	public Optional<BreastPlate> removeBreastplate() {
		return Optional.ofNullable(ep.unEquipBreastplate());
	}
	
	/**
	 * Gets the health stat to use
	 * @return The health Stat object
	*/
	public Stat getHealthStat() {
		return statArray[0];
	}
}
